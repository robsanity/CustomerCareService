package it.polito.wa2.g35.server.ticketing.TicketStatus

import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
import it.polito.wa2.g35.server.profiles.Employee.Expert.Expert

import java.util.Date

data class TicketStatusDTO (
    val id: Long?,
    val statusTimestamp: Date?,
    val status: TicketStatusValues,
    val description: String?,
    val ticket: Ticket,
    val expert: Expert
)

fun TicketStatus.toDTO() : TicketStatusDTO {
    return TicketStatusDTO(this.id, this.statusTimestamp, this.status, this.description, this.ticket, this.expert)
}