package it.polito.wa2.g35.server.ticketing.Ticket

interface TicketService {
    fun getAll() : List<TicketDTO>

    fun getTicketById(id: Long) : TicketDTO?

    fun getTicketsByStatus(status: String): List<TicketDTO>

    fun getTicketsByExpert(idExpert: String): List<TicketDTO>

    fun getTicketsByPriority(priority: Int): List<TicketDTO>

    fun getTicketsByCustomer(idCustomer: String) : List<TicketDTO>

    fun createTicket(ticket: TicketDTO) : TicketDTO?

    fun updateTicket(ticket: TicketDTO) : TicketDTO?


}