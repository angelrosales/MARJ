package com.transactions.restapi.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transaction")
data class Transaction (val idn:Long = 0, val fromAccount:Long = 0, val toAccount:Long =0, val amount:Float = 0F, var sentAt: LocalDateTime? = null){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0
}