package it.polito.wa2.g35.server.ticketing.ticket;

import it.polito.wa2.g35.server.exceptions.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class TicketController(private val ticketService: TicketService) {
    @GetMapping("/API/tickets")
    @ResponseStatus(HttpStatus.OK)
    fun getTickets(): List<TicketDTO>? {
        return ticketService.getAll()
    }

    @GetMapping("/API/tickets/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    fun getTicketById(@PathVariable ticketId:Long): TicketDTO? {
        return ticketService.getTicketById(ticketId)
    }

    @GetMapping("/API/tickets/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    fun getTicketsByStatus(@PathVariable status: String): List<TicketDTO>?{
        return ticketService.getTicketsByStatus(status)
    }

    @GetMapping("/API/tickets/expert/{expertId}")
    @ResponseStatus(HttpStatus.OK)
    fun getTicketsByExpert(@PathVariable expertId: String): List<TicketDTO>?{
        return ticketService.getTicketsByExpert(expertId)
    }

    @GetMapping("/API/tickets/priority/{priority}")
    @ResponseStatus(HttpStatus.OK)
    fun getTicketsByPriority(@PathVariable priority: String): List<TicketDTO>?{
        return ticketService.getTicketsByPriority(priority)
    }

    @GetMapping("/API/tickets/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    fun getTicketsByCustomer(@PathVariable customerId: String): List<TicketDTO>?{
        return ticketService.getTicketsByCustomer(customerId)
    }

    @PostMapping("/API/tickets/")
    @ResponseStatus(HttpStatus.CREATED)
    fun postTicket(
        @RequestBody ts: TicketInputDTO
    ) : TicketDTO? {
        return ticketService.createTicket(ts)
    }

    @PutMapping("/API/tickets/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTicket(
        @PathVariable("ticketId") ticketId: Long,
        @RequestBody p: TicketInputDTO,
        br: BindingResult
    ) : TicketDTO? {
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            if (ticketId == p.id) {
                return ticketService.updateTicket(p)
            } else
                throw TicketConflictException("Ticket with given id doesn't exists!")
    }

    @PatchMapping("/API/tickets/{ticketId}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTicketStatus(
        @PathVariable("ticketId") ticketId: Long,
        @PathVariable("status") status: String
    ) {
        ticketService.updateTicketStatus(ticketId, status)
    }

    @PatchMapping("/API/tickets/{ticketId}/priority/{priority}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTicketPriority(
        @PathVariable("ticketId") ticketId: Long,
        @PathVariable("priority") priority: String
    ) {
        ticketService.updateTicketPriority(ticketId, priority)
    }
}