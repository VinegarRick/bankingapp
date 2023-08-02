package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.BankTransaction;

public interface BankTransactionService {
	BankTransaction save(BankTransaction bankTransaction);
	BankTransaction find(Long bankTransactionId);
	List<BankTransaction> findAll();
	void delete(Long bankTransactionId);
	boolean exists(Long bankTransactionId);
}
