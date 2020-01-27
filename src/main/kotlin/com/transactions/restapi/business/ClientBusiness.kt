package com.transactions.restapi.business

import com.transactions.restapi.dao.ClientRepository
import com.transactions.restapi.exceptions.BusinessExceptions
import com.transactions.restapi.exceptions.NotFoundException
import com.transactions.restapi.model.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientBusiness : IClientBusiness {
    @Autowired
    val clientRepository: ClientRepository? = null

    @Throws(BusinessExceptions::class)
    override fun list(): List<Client> {
        try {
            return clientRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
    }

    @Throws(BusinessExceptions::class)
    override fun load(idClient: Long): Client {
        val op: Optional<Client>
        try {
            op = clientRepository!!.findById(idClient)
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontró la registro con  $idClient")
        }
        return op.get()
    }

    @Throws(BusinessExceptions::class)
    override fun save(client: Client): Client {
        try {
            return clientRepository!!.save(client)
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
    }

    @Throws(BusinessExceptions::class)
    override fun remove(idClient: Long) {
        val op: Optional<Client>
        try {
            op = clientRepository!!.findById(idClient)
        } catch (e: Exception) {
            throw BusinessExceptions(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontro la transaccion $idClient")
        }else {
            try {
                clientRepository!!.deleteById(idClient)
            } catch (e: Exception) {
                throw BusinessExceptions(e.message)
            }
        }
    }
}