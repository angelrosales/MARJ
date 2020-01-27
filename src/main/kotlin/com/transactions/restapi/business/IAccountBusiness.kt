package com.transactions.restapi.business

import com.transactions.restapi.model.Account

interface IAccountBusiness {
    fun list(): List<Account>
    fun load(idAccount: Long): Account
    fun save(account: Account): Account
    fun remove(idAccount: Long)
    fun findByAccount(account: Long): Account
   }


