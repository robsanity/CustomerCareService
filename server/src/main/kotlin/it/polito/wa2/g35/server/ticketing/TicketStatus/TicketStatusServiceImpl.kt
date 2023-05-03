package it.polito.wa2.g35.server.ticketing.TicketStatus

import it.polito.wa2.g35.server.ticketing.Ticket.TicketService
import org.springframework.stereotype.Service
import java.util.Date


@Service
class TicketStatusServiceImpl(private val ticketStatusRepository: TicketStatusRepository) : TicketStatusService{

    override fun createTicketStatus(ticketStatus: TicketStatusDTO): TicketStatusDTO? {
        return ticketStatusRepository.save(TicketStatus(ticketStatus.id, Date(), ticketStatus.status, ticketStatus.description, ticketStatus.ticket, ticketStatus.expert )).toDTO()
    }
}