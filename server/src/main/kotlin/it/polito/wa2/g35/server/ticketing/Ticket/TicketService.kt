package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusValues

interface TicketService {
    fun getAll() : List<TicketDTO>

    fun getTicketById(id: Long) : TicketDTO?

    fun getTicketsByStatus(status: TicketStatusValues): List<TicketDTO>

    fun getTicketsByExpert(idExpert: String): List<TicketDTO>

    fun getTicketsByPriority(priority: TicketPriority): List<TicketDTO>

    fun getTicketsByCustomer(idCustomer: String) : List<TicketDTO>

    fun createTicket(ticket: TicketDTO) : TicketDTO?

    fun updateTicket(ticket: TicketDTO) : TicketDTO?


}