package com.synergisticit.service;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.BankTransaction;
import com.synergisticit.repository.BankTransactionRepository;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

	@Autowired
	BankTransactionRepository bankTransactionRepository;
	
	@Override
	public BankTransaction save(BankTransaction bankTransaction) {
		return bankTransactionRepository.save(bankTransaction);
	}

	@Override
	public BankTransaction find(Long bankTransactionId) {
		Optional<BankTransaction> bankTransaction = bankTransactionRepository.findById(bankTransactionId);
		if (bankTransaction.isPresent()) {
			return bankTransaction.get();
		} else {
			return null;
		}
	}

	@Override
	public List<BankTransaction> findAll() {
		List<BankTransaction> bankTransactions = new ArrayList<>();
		Iterable<BankTransaction> iterable = bankTransactionRepository.findAll();
		Iterator<BankTransaction> itr = iterable.iterator();
		
		while (itr.hasNext()) {
			bankTransactions.add(itr.next());
		}
		
		return bankTransactions;
	}

	@Override
	public void delete(Long bankTransactionId) {
		bankTransactionRepository.deleteById(bankTransactionId);

	}

	@Override
	public boolean exists(Long bankTransactionId) {
		return bankTransactionRepository.existsById(bankTransactionId);
	}

}
