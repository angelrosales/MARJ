API REST que simula la tranferencia entre cuentas
y el historial de tranferencias
desarrollado con Kotlin, Spring Boot, Maven para 
las depaendnencias.
---------------------------------------------------------
tranferencia peticion  POST
http://localhost:8080/api/v1/operations/transfer
{
"fromAccount": 10001,
"toAccount": 10002,
"amount": 99.54
}

descripition : tanferencia de cuenta "fromAccount" a
"toAccount" por un monto de "amount"

--------------------------------------------------------

hitorial de operaciones peticion GET
http://localhost:8080/api/v1/operations

[
{
"idn": 12345,
"fromAccount": 10001,
"toAccount": 10002,
"amount": 2323,
"sentAt": null,
"id": 3
},
{
"idn": 12346,
"fromAccount": 10002,
"toAccount": 343434,
"amount": 2323,
"sentAt": null,
"id": 4
}
]

-----------------------------------------------------------
datos de una cuenta peticion GET
http://localhost:8080/api/v1/operations/account/10002


{
"dni": 10002,
"account": 10002,
"balance": 100,
"owner": 12346,
"createdAt": "2020-01-17T09:45:24.945625",
"id": 2
}
