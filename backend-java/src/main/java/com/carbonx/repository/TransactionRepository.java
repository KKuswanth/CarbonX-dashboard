package com.carbonx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carbonx.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
