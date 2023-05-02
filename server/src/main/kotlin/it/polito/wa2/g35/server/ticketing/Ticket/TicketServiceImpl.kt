package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.profiles.Customer.Customer
import it.polito.wa2.g35.server.profiles.Customer.CustomerDTO
import it.polito.wa2.g35.server.profiles.Customer.toDTO
import it.polito.wa2.g35.server.profiles.DuplicateProfileException
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

        val listTicket = ticketRepository.getTicketsByStatus(status)?.map { it.toDTO() }
        if (listTicket != null)
            return listTicket
        else
            return emptyList()
    }

    override fun getTicketsByExpert(idExpert: String): List<TicketDTO> {
        // if(idExpert != /TODO/ get all experts id )
        val listTicket = ticketRepository.getTicketsByExpert(idExpert)?.map { it.toDTO() }
        if (listTicket != null)
            return listTicket
        else
            return emptyList()
    }

    override fun getTicketsByPriority(priority: TicketPriority): List<TicketDTO> {
        /* if (status not in TicketPriority /TODO/ trattare eccezione */
        val listTicket = ticketRepository.getTicketsByPriority(priority)?.map { it.toDTO() }
        if (listTicket != null)
            return listTicket
        else
            return emptyList() 
    }

    override fun getTicketsByCustomer(idCustomer: String): List<TicketDTO> {
        // if (customer != possible customer -> exception
        val listTicket = ticketRepository.getTicketsByCustomer(idCustomer)?.map { it.toDTO() }
        if (listTicket != null)
            return listTicket
        else
            return emptyList()
    }

    override fun createTicket(ticket: TicketDTO): TicketDTO? {
        return if (ticket != null) {
            val checkIfTicketExsist = ticketRepository.findByIdOrNull(ticket.id)
            if(checkIfTicketExsist == null){
                ticketRepository.save(Ticket(ticket.id,ticket.creationTimeStamp,ticket.issueDescription,ticket.priority,ticket.status,ticket.expert,ticket.product,ticket.customer,ticket.statusHistory,ticket.messages)).toDTO()
            } else {
                throw DuplicateProfileException("Profile with given email already exists!")
            }
        } else null
    }


    override fun updateTicket(ticket: TicketDTO): TicketDTO? {
        TODO("Not yet implemented")
    }

}