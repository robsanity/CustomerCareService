package it.polito.wa2.g35.server.ticketing.Ticket;

import it.polito.wa2.g35.server.exceptions.BadRequestException
import it.polito.wa2.g35.server.profiles.Customer.CustomerService
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
    fun getTicketsByStatus(@PathVariable status: String): List<TicketDTO>?{
        val statusValue = TicketStatusValues.valueOf(status.uppercase())
        return ticketService.getTicketsByStatus(statusValue)
    }

    @GetMapping("/API/tickets/expert/{expertId}")
    fun getTicketsByExpert(@PathVariable expertId: String): List<TicketDTO>?{
        return ticketService.getTicketsByExpert(expertId)
    }

    @GetMapping("/API/tickets/priority/{priority}")
    fun getTicketsByPriority(@PathVariable priority: String): List<TicketDTO>?{
        val priorityValue = TicketPriority.valueOf(priority.uppercase())
        return ticketService.getTicketsByPriority(priorityValue)
    }

    @GetMapping("/API/tickets/customer/{customerId}")
    fun getTicketsByCustomer(@PathVariable customerId: String): List<TicketDTO>?{
        return ticketService.getTicketsByCustomer(customerId)
    }

    @PostMapping("/API/tickets/")
    @ResponseStatus(HttpStatus.CREATED)
    fun postTicket(
        @RequestBody ts: TicketInputDTO
    ) {
        ticketService.createTicket(ts)
    }

    @PutMapping("/API/tickets/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTicket(
        @PathVariable("ticketId") ticketId: Long,
        @RequestBody p: TicketInputDTO,
        br: BindingResult
    ) {
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            if (ticketId == p.id) {
                ticketService.updateTicket(p)
            } else
                throw TicketConflictException("Ticket with given id doesn't exists!")
    }

    @PatchMapping("/API/tickets/{ticketId}/status/{status}")
    fun updateTicketStatus(
        @PathVariable("ticketId") ticketId: Long,
        @PathVariable("status") status: String
    ) {
        ticketService.updateTicketStatus(ticketId, status)
    }

    @PatchMapping("/API/tickets/{ticketId}/priority/{priority}")
    fun updateTicketPriority(
        @PathVariable("ticketId") ticketId: Long,
        @PathVariable("priority") priority: String
    ) {
        ticketService.updateTicketPriority(ticketId, priority)
    }
}