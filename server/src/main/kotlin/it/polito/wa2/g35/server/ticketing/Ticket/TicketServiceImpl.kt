package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusValues
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class TicketServiceImpl (
    val ticketRepository: TicketRepository
) : TicketService {
    override fun getAll(): List<TicketDTO> {
        return ticketRepository.findAll().map { it.toDTO() }
    }

    override fun getTicketById(id: Long): TicketDTO? {
        val ticket = ticketRepository.findByIdOrNull(id)?.toDTO()
        if (ticket != null)
            return ticket
        else
            throw TicketNotFoundException("Ticket not found!")
    }

    override fun getTicketsByStatus(status: TicketStatusValues): List<TicketDTO> {
        /* if (status not in TicketStatusValues /TODO/ trattare eccezione */

        val listTicket = ticketRepository.findAll().filter { it.status.compareTo(status) == 0 }.map { it.toDTO() }
        if (!listTicket.isEmpty())
            return listTicket
        else
            return emptyList()
    }

    override fun getTicketsByExpert(idExpert: String): List<TicketDTO> {
        // if(idExpert != /TODO/ get all experts id )
        val listTicket = ticketRepository.findAll().filter { it.expert.id.compareTo(idExpert) == 0 }.map { it.toDTO() }
        if (listTicket != null)
            return listTicket
        else
            return emptyList()
    }

    override fun getTicketsByPriority(priority: TicketPriority): List<TicketDTO> {
        /* if (status not in TicketPriority /TODO/ trattare eccezione */
        val listTicket = ticketRepository.findAll().filter { it.priority.compareTo(priority) == 0 }.map { it.toDTO() }
        if (listTicket != null)
            return listTicket
        else
            return emptyList() 
    }

    override fun getTicketsByCustomer(idCustomer: String): List<TicketDTO> {
        // if (customer != possible customer -> exception
        val listTicket = ticketRepository.findAll().filter { it.customer.email.compareTo(idCustomer) == 0 }.map { it.toDTO() }
        if (listTicket != null)
            return listTicket
        else
            return emptyList()
    }

    override fun createTicket(ticket: TicketDTO): TicketDTO? {
        TODO("Not yet implemented")
    }

    override fun updateTicket(ticket: TicketDTO): TicketDTO? {
        TODO("Not yet implemented")
    }

}