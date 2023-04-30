package it.polito.wa2.g35.server.ticketing.TicketStatus

import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
import it.polito.wa2.g35.server.profiles.Employee.Expert.Expert

import java.util.Date

data class TicketStatusDTO (
    val id: String?,
    val timestamp: Date,
    val status: TicketStatusValues,
    val description: String,
    val ticket: Ticket,
    val expert: Expert
)