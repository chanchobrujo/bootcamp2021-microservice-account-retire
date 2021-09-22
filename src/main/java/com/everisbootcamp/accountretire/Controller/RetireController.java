package com.everisbootcamp.accountretire.Controller;

import com.everisbootcamp.accountretire.Data.Retire;
import com.everisbootcamp.accountretire.Model.RetireModel;
import com.everisbootcamp.accountretire.Service.RetireService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class RetireController {

    @Autowired
    private RetireService service;

    @GetMapping("/")
    public Mono<ResponseEntity<Flux<Retire>>> findByAll() {
        return Mono.just(ResponseEntity.ok().body(service.findAll()));
    }

    @PostMapping("/save/{numberaccount}")
    public Mono<ResponseEntity<Map<String, Object>>> save(
        @PathVariable("numberaccount") String numberaccount,
        @RequestBody @Valid RetireModel model,
        BindingResult bindinResult
    ) {
        if (bindinResult.hasErrors()) return service.BindingResultErrors(bindinResult);
        return service
            .save(numberaccount, model)
            .map(
                response -> {
                    return ResponseEntity.status(response.getStatus()).body(response.getResponse());
                }
            )
            .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}
