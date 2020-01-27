package com.transactions.restapi.dao

import com.transactions.restapi.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository: JpaRepository<Account, Long>{
    fun findByAccount(account: Long): Optional<Account>
}