package com.transactions.restapi.business

import com.transactions.restapi.model.Transaction

interface ITransactionBusiness {
    fun list(): List<Transaction>
    fun load(idTransaction: Long): Transaction
    fun save(transaction:Transaction): Transaction
    fun remove(idTransaction: Long)
}