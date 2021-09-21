package com.everisbootcamp.accountretire.Model; 

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor 
@ToString
@AllArgsConstructor
public class RetireModel { 

    @NotBlank(message = "El campo número de cuenta no debe estar vacio.")
	private String numberaccount;
	private Double amount;

}
