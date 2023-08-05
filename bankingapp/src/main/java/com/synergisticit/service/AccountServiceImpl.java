package com.synergisticit.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Account;
import com.synergisticit.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account find(Long accountId) {
		Optional<Account> account = accountRepository.findById(accountId);
		if (account.isPresent()) {
			return account.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<>();
		Iterable<Account> iterable = accountRepository.findAll();
		Iterator<Account> itr = iterable.iterator();
		
		while (itr.hasNext()) {
			accounts.add(itr.next());
		}
		
		return accounts;
	}

	@Override
	public void delete(Long accountId) {
		accountRepository.deleteById(accountId);
	}

	@Override
	public boolean exists(Long accountId) {
		return accountRepository.existsById(accountId);
	}

}
