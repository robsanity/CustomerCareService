package it.polito.wa2.g35.server.ticketing.Ticket;

import it.polito.wa2.g35.server.exceptions.BadRequestException
import it.polito.wa2.g35.server.profiles.Customer.CustomerDTO
import it.polito.wa2.g35.server.profiles.Customer.CustomerRepository
import it.polito.wa2.g35.server.profiles.Customer.CustomerService
import it.polito.wa2.g35.server.profiles.Customer.toDTO
import it.polito.wa2.g35.server.profiles.ProfileNotFoundException
import it.polito.wa2.g35.server.ticketing.Order.OrderDTO
import it.polito.wa2.g35.server.ticketing.Order.OrderService
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusDTO
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusValues
import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class TicketController(private val ticketService: TicketService, private val customerService: CustomerService) {

    @GetMapping("/API/tickets")
    fun getTickets(): List<TicketDTO>? {
        return ticketService.getAll()
    }

    @GetMapping("/API/tickets/{ticketId}")
    fun getTicketById(@PathVariable ticketId:Long): TicketDTO? {
        return ticketService.getTicketById(ticketId)
    }

    @GetMapping("/API/tickets/status/{status}")
    fun getTicketsByStatus(@PathVariable status: TicketStatusValues): List<TicketDTO>?{
        return ticketService.getTicketsByStatus(status)
    }

    @GetMapping("/API/tickets/expert/{expertId}")
    fun getTicketsByExpert(@PathVariable expert: String): List<TicketDTO>?{
        return ticketService.getTicketsByExpert(expert)
    }

    @GetMapping("/API/tickets/priority/{priority}")
    fun getTicketsByPriority(@PathVariable priority: TicketPriority): List<TicketDTO>?{
        return ticketService.getTicketsByPriority(priority)
    }

    @GetMapping("/API/tickets/customer/{customer}")
    fun getTicketsByCustomer(@PathVariable customerId: String): List<TicketDTO>?{
        return ticketService.getTicketsByCustomer(customerId)
    }

    @PostMapping("/API/tickets/")
    @ResponseStatus(HttpStatus.CREATED)
    fun postTicket(
        @RequestBody @Valid ts: TicketDTO,
        br: BindingResult
    ) {
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else {
            val customerId = ts.id
            var customer = customerService.getCustomer(customerId.toString())
            if (customer != null) {
                ts.customer = customer
            }
            ticketService.createTicket(ts)
        }
    }

    @PutMapping("/API/tickets/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateProfile(
        @PathVariable("ticketId") ticketId: Long,
        @RequestBody @Valid p: TicketDTO,
        br: BindingResult
    ) {
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            if (ticketId == p.id) {
                ticketService.updateTicket(p)
            } else
                throw ProfileNotFoundException("Ticket with given email doesn't exists!")
    }

}