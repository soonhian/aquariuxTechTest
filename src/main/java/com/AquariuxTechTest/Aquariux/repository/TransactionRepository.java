package com.AquariuxTechTest.Aquariux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AquariuxTechTest.Aquariux.entity.CryptoCoinPair;
import com.AquariuxTechTest.Aquariux.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
