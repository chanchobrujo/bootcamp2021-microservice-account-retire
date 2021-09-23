package com.everisbootcamp.accountretire.Service;

import com.everisbootcamp.accountretire.Constant.Constants;
import com.everisbootcamp.accountretire.Data.Retire;
import com.everisbootcamp.accountretire.Interface.RetireRepository;
import com.everisbootcamp.accountretire.Model.AccountModel;
import com.everisbootcamp.accountretire.Model.ResponseModel;
import com.everisbootcamp.accountretire.Model.RetireModel;
import com.everisbootcamp.accountretire.Model.RulesModel;
import com.everisbootcamp.accountretire.Model.updateBalanceModel;
import com.everisbootcamp.accountretire.Web.Consumer;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RetireService {

    @Autowired
    private RetireRepository repository;

    private ResponseEntity<AccountModel> findAccountByNumberAccount(String number) {
        return Consumer.webclientAccount
            .get()
            .uri("/".concat(number))
            .retrieve()
            .onStatus(status -> status.value() == 404, clientResponse -> Mono.empty())
            .toEntity(AccountModel.class)
            .block();
    }

    private void updateBalance(String numberaccount, Double balance) {
        Consumer.webclientAccount
            .post()
            .uri("/updateBalance")
            .body(Mono.just(new updateBalanceModel(numberaccount, balance)), updateBalanceModel.class)
            .retrieve()
            .bodyToMono(Object.class)
            .subscribe();
    }

    private int getMonthlyMovementsQuantity(String numberaccount) {
        return (int) repository.findAll().toStream().filter(d -> d.getNumberaccount().equals(numberaccount)).count();
    }

    public Mono<ResponseEntity<Map<String, Object>>> BindingResultErrors(BindingResult bindinResult) {
        ResponseModel response = new ResponseModel(
            bindinResult.getAllErrors().stream().findFirst().get().getDefaultMessage().toString(),
            HttpStatus.NOT_ACCEPTABLE
        );

        return Mono.just(ResponseEntity.internalServerError().body(response.getResponse()));
    }

    public Mono<ResponseModel> save(String numberaccount, RetireModel model) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = Constants.Messages.INVALID_DATA;

        if (findAccountByNumberAccount(numberaccount).getBody() != null) {
            RulesModel rules = findAccountByNumberAccount(numberaccount).getBody().getRules();
            Double amountaccount = findAccountByNumberAccount(numberaccount).getBody().getAmount();

            if (
                rules.isMaximumLimitMonthlyMovements() && rules.getMaximumLimitMonthlyMovementsQuantity() <= getMonthlyMovementsQuantity(numberaccount)
            ) return Mono.just(new ResponseModel(Constants.Messages.MOVEMENT_DENIED, HttpStatus.NOT_ACCEPTABLE));

            if (model.getAmount() < amountaccount && amountaccount > 0) {
                status = HttpStatus.CREATED;
                message = Constants.Messages.CORRECT_DATA;

                Double newamount = amountaccount - model.getAmount();
                updateBalance(numberaccount, newamount);

                Retire retire = new Retire(numberaccount, model.getAmount());
                repository.save(retire).subscribe();
            } else {
                status = HttpStatus.NOT_ACCEPTABLE;
                message = Constants.Messages.BALANCE;
            }
        }

        return Mono.just(new ResponseModel(message, status));
    }

    public Flux<Retire> findAll() {
        return repository.findAll();
    }

    public Mono<Retire> findById(String id) {
        return repository.findById(id);
    }

    public Flux<Retire> findAccountsByDate(String date) {
        return Flux.fromIterable(repository.findAll().toStream().filter(a -> a.getDatecreated().equals(null)).collect(Collectors.toList()));
    }
}
