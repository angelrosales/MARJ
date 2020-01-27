package com.transactions.restapi.business

import com.transactions.restapi.model.Client

interface IClientBusiness {
    fun list(): List<Client>
    fun load(idClient: Long): Client
    fun save(client:Client): Client
    fun remove(idClient: Long)
}