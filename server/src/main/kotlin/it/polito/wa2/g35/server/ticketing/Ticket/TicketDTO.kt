package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.Customer.Customer
import it.polito.wa2.g35.server.profiles.Expert.Expert
import it.polito.wa2.g35.server.ticketing.Message.Message
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatus
import java.util.Date

data class TicketDTO(
    val id: Long,
    val creationTimeStamp: Date,
    val issueDescription: String,
    val priority: Int,
    val status: String,
    val expert: Expert,
    val product: Product,
    val customer: Customer,
    val statusHistory: MutableSet<TicketStatus>,
    val messages: MutableSet<Message>
)