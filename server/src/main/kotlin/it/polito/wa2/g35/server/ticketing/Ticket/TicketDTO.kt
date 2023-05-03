package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.Customer.Customer
import it.polito.wa2.g35.server.profiles.Employee.Expert.Expert
import it.polito.wa2.g35.server.profiles.Employee.Expert.ExpertDTO
import it.polito.wa2.g35.server.ticketing.Message.Message
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatus
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusValues
import jakarta.persistence.Id
import java.util.Date

data class TicketDTO(
    val id: Long?,
    val creationTimeStamp: Date,
    val issueDescription: String,
    val priority: TicketPriority,
    val status: TicketStatusValues,
    val expert: Expert,
    val product: Product,
    var customer: Customer,
    val statusHistory: MutableSet<TicketStatus>,
)

fun Ticket.toDTO(): TicketDTO {
    return TicketDTO(this.id, this.creationTimeStamp, this.issueDescription, this.priority, this.status, this.expert, this.product, this.customer, this.statusHistory)
}

