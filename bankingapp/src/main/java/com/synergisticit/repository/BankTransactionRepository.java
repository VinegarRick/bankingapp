package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.synergisticit.domain.BankTransaction;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {
    
    @Modifying
    @Query("UPDATE Account fromAcc SET fromAcc.accountBalance = fromAcc.accountBalance - :transactionAmount WHERE fromAcc.id = :fromAccountId")
    void updateAccountBalanceSubtractFrom(@Param("fromAccountId") Long fromAccountId, @Param("transactionAmount") double transactionAmount);

    @Modifying
    @Query("UPDATE Account toAcc SET toAcc.accountBalance = toAcc.accountBalance + :transactionAmount WHERE toAcc.id = :toAccountId")
    void updateAccountBalanceAddTo(@Param("toAccountId") Long toAccountId, @Param("transactionAmount") double transactionAmount);
}
