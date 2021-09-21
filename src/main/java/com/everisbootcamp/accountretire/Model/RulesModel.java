package com.everisbootcamp.accountretire.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RulesModel { 
    private String customerType;
    private boolean commissionMaintenance; 
    private boolean maximumLimitMonthlyMovements;
    private Integer maximumLimitMonthlyMovementsQuantity;
}
