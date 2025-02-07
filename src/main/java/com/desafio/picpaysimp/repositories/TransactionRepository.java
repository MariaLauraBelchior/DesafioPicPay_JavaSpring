package com.desafio.picpaysimp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.picpaysimp.domain.transaction.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
    
}
