package com.synergisticit.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bankTransactionId;
	
	// this can be used for deposits and transfers
	//@ManyToOne
	//@JoinColumn(name="accountId")
	private Long bankTransactionToAccount;
	
	// this can be used for withdrawal and transfers
	//@ManyToOne
	//@JoinColumn(name="accountId")
	private Long bankTransactionFromAccount;
	
	private double transactionAmount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime bankTransactionDateTime;
	
	private String comments;
}
