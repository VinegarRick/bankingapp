package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.BankTransaction;

public interface BankTransactionService {
	BankTransaction save(BankTransaction bankTransaction);
	BankTransaction find(Long bankTransactionId);
	List<BankTransaction> findAll();
	void delete(Long bankTransactionId);
	boolean exists(Long bankTransactionId);
	void balanceTransfer(Long fromAccountId, Long toAccountId, double amount);
	void subtractBalance(Long fromAccountId, double amount);
	void addBalance(Long toAccountId, double amount);
}
