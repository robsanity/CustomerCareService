package it.polito.wa2.g35.server.ticketing.TicketStatus

import it.polito.wa2.g35.server.exceptions.BadRequestException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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