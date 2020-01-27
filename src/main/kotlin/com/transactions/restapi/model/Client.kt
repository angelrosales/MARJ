package com.transactions.restapi.model

import javax.persistence.*

@Entity
@Table(name = "client")
data class Client(val dni:Long = 0, val owner:Long = 0, val name: String ="" ) {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   var id:Long = 0
}