package com.everisbootcamp.accountretire.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountModel {

    private String idaccount;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime datecreated;

    private String idcustomer;
    private String typeaccount;
    private String profile;
    private Double amount;

    private RulesModel rules;
}
