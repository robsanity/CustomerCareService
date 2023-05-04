package it.polito.wa2.g35.server.ticketing.ticketStatus

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
class TicketStatusController(private val ticketStatusService: TicketStatusService){
    @PostMapping("/API/status/")
    @ResponseStatus(HttpStatus.CREATED)
    fun postTicketStatus(
        @RequestBody ts: TicketStatusDTO,
    ) {
        ticketStatusService.createTicketStatus(ts)
    }

    @GetMapping("/API/status/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    fun getTicketStatusByTicketId(
        @PathVariable ticketId: Long
    ) : List<TicketStatusDTO> {
       return ticketStatusService.getTicketStatusesByTicketId(ticketId)
    }
}