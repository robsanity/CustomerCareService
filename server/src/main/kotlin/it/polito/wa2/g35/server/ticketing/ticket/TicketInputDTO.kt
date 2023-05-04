package it.polito.wa2.g35.server.ticketing.ticket


import java.util.Date

class TicketInputDTO (
    val id: Long?,
    val creationTimestamp: Date?,
    val issueDescription: String,
    val priority: String?,
    val status: String?,
    val expertId: String?,
    val productId: String,
    var customerId: String,
   )