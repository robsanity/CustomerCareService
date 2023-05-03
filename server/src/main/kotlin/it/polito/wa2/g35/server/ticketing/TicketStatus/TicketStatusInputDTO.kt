package it.polito.wa2.g35.server.ticketing.TicketStatus

import it.polito.wa2.g35.server.ticketing.Ticket.TicketStatusValues
import java.util.*

data class TicketStatusInputDTO (
    val id: Long?,
    val statusTimestamp: Date?,
    val status: TicketStatusValues,
    val description: String?,
    val ticketId: Long?,
    val expertId: String?
)