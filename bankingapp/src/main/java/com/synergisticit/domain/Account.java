package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long accountId;
	
	private String accountHolder;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate accountDateOpen;
	
	private double accountBalance;
	
	@JsonIgnore
	@JoinColumn(name="branchId")
	@ManyToOne
	private Branch accountBranch;
	
	@JsonIgnore
	@JoinColumn(name="customerId")
	@ManyToOne
	private Customer accountCustomer;
	
	@Transient
	private Long branchId;
	
	@Transient
	private Long customerId;
	
}
