package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Account;

public interface AccountService {
	Account save(Account account);
	Account find(Long accountId);
	List<Account> findAll();
	void delete(Long accountId);
	boolean exists(Long accountId);
}
