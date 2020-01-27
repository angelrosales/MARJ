package com.transactions.restapi.dao

import com.transactions.restapi.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long>