package com.transactions.restapi.business

import com.transactions.restapi.dao.AccountRepository
import com.transactions.restapi.exceptions.BusinessExceptions
import com.transactions.restapi.exceptions.NotFoundException
import com.transactions.restapi.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountBusiness : IAccountBusiness {
    override fun findByAccount(account: Long): Account {
        val op: Optional<Account>
        try {
            op = accountRepository!!.findByAccount(account)

        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la registro con  $account")
        }
        return op.get()
    }

    @Autowired
    val accountRepository: AccountRepository? = null

    @Throws(BusinessExceptions::class)
    override fun list(): List<Account> {
        try {
            return accountRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
    }



    @Throws(BusinessExceptions::class, NotFoundException::class)
    override fun load(idAccount: Long): Account {
        val op: Optional<Account>
        try {
           op = accountRepository!!.findById(idAccount)

        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la registro con  $idAccount")
        }
        return op.get()
    }

    @Throws(BusinessExceptions::class)
    override fun save(account: Account): Account {
        try {
            return accountRepository!!.save(account)
        } catch (e:Exception) {
            throw BusinessExceptions(e.message)
        }
    }

    @Throws(BusinessExceptions::class)
    override fun remove(idAccount: Long) {
        val op: Optional<Account>
        try {
            op = accountRepository!!.findById(idAccount)
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la cuenta $idAccount")
        }else {
            try {
                accountRepository!!.deleteById(idAccount)
            } catch (e: Exception) {
                throw BusinessExceptions(e.message)
            }
        }
    }
}