package it.polito.wa2.g35.server.ticketing.ticket

import it.polito.wa2.g35.server.products.ProductNotFoundException
import it.polito.wa2.g35.server.products.ProductService
import it.polito.wa2.g35.server.products.toProduct
import it.polito.wa2.g35.server.profiles.customer.CustomerService
import it.polito.wa2.g35.server.profiles.customer.toCustomer
import it.polito.wa2.g35.server.profiles.employee.expert.ExpertService
import it.polito.wa2.g35.server.profiles.employee.expert.toExpert
import it.polito.wa2.g35.server.profiles.ProfileNotFoundException
import it.polito.wa2.g35.server.ticketing.order.OrderNotFoundException
import it.polito.wa2.g35.server.ticketing.order.OrderService
import it.polito.wa2.g35.server.ticketing.order.WarrantyExpiredException
import it.polito.wa2.g35.server.ticketing.ticketStatus.TicketStatusDTO
import it.polito.wa2.g35.server.ticketing.ticketStatus.TicketStatusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class TicketServiceImpl(
    val ticketRepository: TicketRepository
) : TicketService {

    @Autowired
    lateinit var expertService: ExpertService

    @Autowired
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var ticketStatusService: TicketStatusService

    @Autowired
    lateinit var orderService: OrderService

    override fun getAll(): List<TicketDTO> {
        return ticketRepository.findAll().map { it.toDTO() }
    }

    override fun getTicketById(id: Long): TicketDTO? {
        return ticketRepository.findByIdOrNull(id)?.toDTO() ?: throw TicketNotFoundException("Ticket not found!")
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
        customerService.getCustomerByEmail(idCustomer)
            ?: throw ProfileNotFoundException("Customer not found with this Id!")
        val listTicket = ticketRepository.getTicketsByCustomerEmail(idCustomer)?.map { it.toDTO() }
        return listTicket ?: emptyList()
    }

    @Transactional
    override fun createTicket(ticket: TicketInputDTO): TicketDTO? {
        val customer = customerService.getCustomerByEmail(ticket.customerId)
            ?: throw ProfileNotFoundException("Customer not found with this id!")
        val product = productService.getProductById(ticket.productId)
            ?: throw ProductNotFoundException("Product not found with this id!")
        val warranty = orderService.getOrderByCustomerAndProduct(customer.email, product.id)
            ?: throw OrderNotFoundException("Order not found with this combination of product and customer!")
        if (Date().after(warranty.warrantyDuration)) {
            throw WarrantyExpiredException("Order warranty expired!")
        } else {
            val ticketToSave = ticketRepository.save(
                Ticket(
                    ticket.id,
                    Date(),
                    ticket.issueDescription,
                    null,
                    TicketStatusValues.OPEN,
                    null,
                    product.toProduct(),
                    customer.toCustomer(),
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
    }


    @Transactional
    override fun updateTicket(ticket: TicketInputDTO): TicketDTO? {
        val currentTicket =
            getTicketById(ticket.id!!)?.toTicket() ?: throw TicketNotFoundException("Ticket not found with this id!")
        val expert = expertService.getExpertById(ticket.expertId)?.toExpert()
            ?: throw ProfileNotFoundException("Expert not found with this id!")
        val ticketToUpdate = ticketRepository.save(
            Ticket(
                ticket.id,
                currentTicket.creationTimestamp,
                ticket.issueDescription,
                ticket.priority?.let {
                    try {
                        TicketPriority.valueOf(it.uppercase())
                    } catch (e: IllegalArgumentException) {
                        throw TicketPriorityInvalidException("Ticket Priority not valid!")
                    }
                },
                ticket.status?.let {
                    try {
                        TicketStatusValues.valueOf(it.uppercase())
                    } catch (e: IllegalArgumentException) {
                        throw TicketStatusValueInvalidException("Ticket Status not valid!")
                    }
                },
                expert,
                currentTicket.product,
                currentTicket.customer
            )
        )
        ticketStatusService.createTicketStatus(
            TicketStatusDTO(
                id = null,
                statusTimestamp = null,
                status = ticketToUpdate.status,
                description = ticketToUpdate.issueDescription,
                ticket = ticketToUpdate,
                expert = ticketToUpdate.expert
            )
        )
        return ticketToUpdate.toDTO()
    }

    override fun updateTicketStatus(ticketId: Long, statusValue: String): TicketDTO? {
        var ticket = getTicketById(ticketId)?.toTicket() ?: throw TicketNotFoundException("Ticket not found!")
        val status = try {
            TicketStatusValues.valueOf(statusValue.uppercase())
        } catch (e: IllegalArgumentException) {
            throw TicketStatusValueInvalidException("Ticket Status not valid!")
        }
        ticket.status = status
        ticket = ticketRepository.save(ticket)
        ticketStatusService.createTicketStatus(
            TicketStatusDTO(
                id = null,
                statusTimestamp = null,
                status = ticket.status,
                description = ticket.issueDescription,
                ticket = ticket,
                expert = ticket.expert
            )
        )
        return ticket.toDTO()
    }

    override fun updateTicketPriority(ticketId: Long, priorityValue: String): TicketDTO? {
        val ticket = getTicketById(ticketId)?.toTicket() ?: throw TicketNotFoundException("Ticket not found!")
        val priority = try {
            TicketPriority.valueOf(priorityValue.uppercase())
        } catch (e: IllegalArgumentException) {
            throw TicketPriorityInvalidException("Ticket Priority not valid!")
        }
        ticket.priority = priority
        return ticketRepository.save(ticket).toDTO()
    }

}
