package com.transactions.restapi

import com.transactions.restapi.dao.AccountRepository
import com.transactions.restapi.dao.TransactionRepository
import com.transactions.restapi.model.Account
import com.transactions.restapi.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootApplication
class RestapiApplication:CommandLineRunner{
	@Autowired
	val accountRepository: AccountRepository? = null

	@Autowired
	val transactionRepository: TransactionRepository? = null

	override fun run(vararg args: String?) {

		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		val account1= Account(dni = 10001,account = 10001,balance = 1000F,owner = 12345,createdAt = LocalDateTime.now())
		accountRepository!!.save(account1)
		val account2= Account(dni = 10002,account = 10002,balance = 100F,owner = 12346,createdAt = LocalDateTime.now())
		accountRepository!!.save(account2)

		val transaction1 = Transaction(idn = 12345,fromAccount = 10001,toAccount = 10002,amount = 2323F, sentAt =null)
		transactionRepository!!.save(transaction1);

		val transaction2 = Transaction(idn = 12346,fromAccount = 10002,toAccount = 343434,amount = 2323F)
		transactionRepository!!.save(transaction2);
	}
}

fun main(args: Array<String>) {
	runApplication<RestapiApplication>(*args)
}
