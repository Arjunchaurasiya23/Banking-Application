package com.arjun.banking_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


//automatic generate getter,setter and constructor;

@Data 
@AllArgsConstructor
public class AccountDto {

	
	private Long id;
	private String accountHolderName;
	private double balance;
	
	
	
	
}
