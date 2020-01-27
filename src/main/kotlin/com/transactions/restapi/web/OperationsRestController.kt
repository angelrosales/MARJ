package com.transactions.restapi.web

import com.transactions.restapi.business.IAccountBusiness
import com.transactions.restapi.business.ITransactionBusiness
import com.transactions.restapi.exceptions.BusinessExceptions
import com.transactions.restapi.model.Account
import com.transactions.restapi.model.Transaction
import com.transactions.restapi.utils.Constants
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_OPERATIONS)
class OperationsRestController {

    @Autowired
    val transactionBusiness: ITransactionBusiness? = null

    @Autowired
    val accountBusiness: IAccountBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Transaction>> {
        return try {
            ResponseEntity(transactionBusiness!!.list(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/account")
    fun listAccount(): ResponseEntity<List<Account>> {
        return try {
            ResponseEntity(accountBusiness!!.list(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/account/{id}")
    fun load(@PathVariable(value = "id") idAccount: Long): ResponseEntity<Account> {
        return try {
            ResponseEntity(accountBusiness!!.findByAccount(idAccount), HttpStatus.OK)
        } catch (e: BusinessExceptions) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/transfer")
    fun insert(@RequestBody transaction: Transaction): ResponseEntity<Any> {
        println("transaction");
        return try {
            transactionBusiness!!.save(transaction)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_OPERATIONS + "/" + transaction.id)
            ResponseEntity(responseHeader, HttpStatus.ACCEPTED)
        } catch (e: BusinessExceptions) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
