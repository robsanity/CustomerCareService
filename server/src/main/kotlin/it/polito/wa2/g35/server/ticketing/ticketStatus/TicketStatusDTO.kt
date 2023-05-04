package it.polito.wa2.g35.server.ticketing.ticketStatus

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.customer.Customer
import it.polito.wa2.g35.server.profiles.employee.expert.Expert
import it.polito.wa2.g35.server.ticketing.ticket.Ticket
import it.polito.wa2.g35.server.ticketing.ticket.TicketPriority
import it.polito.wa2.g35.server.ticketing.ticket.TicketStatusValues
import java.util.*

data class TicketStatusDTO (
    val id: Long?,
    val statusTimestamp: Date?,
    val status: TicketStatusValues?,
    val description: String?,
    val ticket: Ticket?,
    val expert: Expert?
)
{
    constructor() : this(null,Date(), TicketStatusValues.valueOf(""),"",Ticket(null,Date(),"",
        TicketPriority.valueOf(""), TicketStatusValues.valueOf(""),Expert("","","","",""),Product("",""),Customer("","","")),Expert("","","","",""))
}


fun TicketStatus.toDTO() : TicketStatusDTO {
    return TicketStatusDTO(this.id, this.statusTimestamp, this.status, this.description, this.ticket, this.expert)
}