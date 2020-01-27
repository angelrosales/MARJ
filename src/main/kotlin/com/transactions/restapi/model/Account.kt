package com.transactions.restapi.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account (val dni:Long = 0, val account:Long = 0, var balance:Float = 0F, val owner:Long = 0, val createdAt: LocalDateTime? =null){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0
}