package com.transactions.restapi.business

import com.transactions.restapi.dao.AccountRepository
import com.transactions.restapi.dao.TransactionRepository
import com.transactions.restapi.exceptions.BusinessExceptions
import com.transactions.restapi.exceptions.NotFoundException
import com.transactions.restapi.model.Account
import com.transactions.restapi.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.System.console
import java.time.LocalDateTime
import java.util.*

@Service
class TransactionBusiness : ITransactionBusiness {
    @Autowired
    val transactionRepository: TransactionRepository? = null

    @Autowired
    val accountRepository: AccountRepository? = null

    @Throws(BusinessExceptions::class)
    override fun list(): List<Transaction> {
        try {
            return transactionRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
    }

    @Throws(BusinessExceptions::class)
    override fun load(idTransaction: Long): Transaction {
        val op: Optional<Transaction>
        try {
            op = transactionRepository!!.findById(idTransaction)
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la registro con  $idTransaction")
        }
        return op.get()
    }

    @Throws(BusinessExceptions::class)
    override fun save(transaction: Transaction): Transaction {

        try {
        val fromAccount = accountRepository!!.findByAccount(transaction.fromAccount)
        if (fromAccount.get().balance - transaction.amount >= -500) {


            fromAccount.get().balance = fromAccount.get().balance - transaction.amount

            val toAccount = accountRepository!!.findByAccount(transaction.toAccount)
            toAccount.get().balance = toAccount.get().balance + transaction.amount

            transaction.sentAt = LocalDateTime.now()
            accountRepository!!.save(fromAccount.get())
            accountRepository!!.save(toAccount.get())



            try {
                return transactionRepository!!.save(transaction)
            } catch (e: Exception) {
                throw BusinessExceptions(e.message)
            }
        }else{
            throw NotFoundException("Saldo insuficuente")
        }
        } catch (e: Exception) {
            throw  NotFoundException("Datos erroneos/Saldo Insuficiente")
        }
    }

    @Throws(BusinessExceptions::class)
    override fun remove(idTransaction: Long) {
        val op: Optional<Transaction>
        try {
            op = transactionRepository!!.findById(idTransaction)
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la transacion: $idTransaction")
        } else {
            try {
                transactionRepository!!.deleteById(idTransaction)
            } catch (e: Exception) {
                throw BusinessExceptions(e.message)
            }
        }
    }
}