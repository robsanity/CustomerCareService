package it.polito.wa2.g35.server.ticketing.ticketStatus

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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
}