package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.products.ProductService
import it.polito.wa2.g35.server.products.toProduct
import it.polito.wa2.g35.server.profiles.Customer.CustomerService
import it.polito.wa2.g35.server.profiles.Customer.toCustomer
import it.polito.wa2.g35.server.profiles.Employee.Expert.ExpertService
import it.polito.wa2.g35.server.profiles.Employee.Expert.toExpert
import it.polito.wa2.g35.server.profiles.ProfileNotFoundException
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusDTO
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusService
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusValues
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class TicketServiceImpl (
    val ticketRepository: TicketRepository
) : TicketService {

    @Autowired
    lateinit var expertService : ExpertService

    @Autowired
    lateinit var customerService : CustomerService

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var ticketStatusService: TicketStatusService

    override fun getAll(): List<TicketDTO> {
        return ticketRepository.findAll().map { it.toDTO() }
    }

    override fun getTicketById(id: Long): TicketDTO? {
        return ticketRepository.findByIdOrNull(id)?.toDTO() ?:
            throw TicketNotFoundException("Ticket not found!")
    }

    override fun getTicketsByStatus(status: TicketStatusValues): List<TicketDTO> {
        val listTicket = ticketRepository.getTicketsByStatus(status)?.map { it.toDTO() }
        return listTicket ?: emptyList()
    }

    override fun getTicketsByExpert(idExpert: String): List<TicketDTO> {
        expertService.getExpertById(idExpert) ?: throw ProfileNotFoundException("No Expert found with this Id!")
        val listTicket = ticketRepository.getTicketsByExpertId(idExpert)?.map { it.toDTO() }
        return listTicket ?: emptyList()
    }

    override fun getTicketsByPriority(priority: TicketPriority): List<TicketDTO> {
        val listTicket = ticketRepository.getTicketsByPriority(priority)?.map { it.toDTO() }
        return listTicket ?: emptyList()
    }

    override fun getTicketsByCustomer(idCustomer: String): List<TicketDTO> {
        customerService.getCustomerByEmail(idCustomer) ?: throw ProfileNotFoundException("Customer not found with this Id!")
        val listTicket = ticketRepository.getTicketsByCustomerEmail(idCustomer)?.map { it.toDTO() }
        return listTicket ?: emptyList()
    }

    @Transactional
    override fun createTicket(ticket: TicketInputDTO): TicketDTO? {
        val customer = customerService.getCustomerByEmail(ticket.customerId)
        val product = productService.getProductById(ticket.productId)
        val ticketToSave = ticketRepository.save(
            Ticket(
                ticket.id,
                Date(),
                ticket.issueDescription,
                null,
                TicketStatusValues.OPEN,
                null,
                product!!.toProduct(),
                customer!!.toCustomer(),
            )
        )
        ticketStatusService.createTicketStatus(
            TicketStatusDTO(
                id = null,
                statusTimestamp = null,
                status = TicketStatusValues.OPEN,
                description = ticketToSave.issueDescription,
                ticket = ticketToSave,
                expert = ticketToSave.expert
            )
        )
        return ticketToSave.toDTO()
    }


    @Transactional
    override fun updateTicket(ticket: TicketInputDTO): TicketDTO? {
        val currentTicket = getTicketById(ticket.id!!)?.toTicket() ?: throw TicketNotFoundException("Ticket not found with this id!")
        val expert = expertService.getExpertById(ticket.expertId)?.toExpert() ?: throw ProfileNotFoundException("Expert not found with this id!")
        val ticketToUpdate = TicketDTO(
            ticket.id,
            currentTicket.creationTimestamp,
            ticket.issueDescription,
            TicketPriority.valueOf(ticket.priority!!.uppercase()),
            TicketStatusValues.valueOf(ticket.status!!.uppercase()),
            expert,
            currentTicket.product,
            currentTicket.customer
        )
        val ticketUpdated = ticketRepository.
        return ticketToUpdate

    }

}
