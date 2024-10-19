package com.arjun.banking_app.service;

import java.util.List;

import com.arjun.banking_app.dto.AccountDto;


public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);//method
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposite(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	List<AccountDto> getAllAccount();
	
	void deleteAccount(Long id);
	
	
}
