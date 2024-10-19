package com.arjun.banking_app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import com.arjun.banking_app.dto.AccountDto;
import com.arjun.banking_app.entity.Account;
import com.arjun.banking_app.mapper.Accountmapper;
import com.arjun.banking_app.repository.AccountRepository;

@Service // auto create spring bean for this class
public class AccountServiceImple implements AccountService {

	private AccountRepository accountRepository;

	public AccountServiceImple(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {// accdto->accjpaentity->todatabases
		Account account = Accountmapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);

		return Accountmapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist "));
		return Accountmapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposite(Long id, double amount) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist "));
		double totle = account.getBalance() + amount;
		account.setBalance(totle);
		Account saveAccount = accountRepository.save(account);
		return Accountmapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist "));

		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficent amount");
		}

		double totle = account.getBalance() - amount;
		account.setBalance(totle);
		Account savedAccount = accountRepository.save(account);// save the amount in the db
		return Accountmapper.mapToAccountDto(savedAccount); // update the info
	}

	@Override
	public List<AccountDto> getAllAccount() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> Accountmapper.mapToAccountDto(account)).collect(Collectors.toList());

	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist "));
		
		accountRepository.deleteById(id);
		
	}

}
