package it.polito.wa2.g35.server.ticketing.message

import it.polito.wa2.g35.server.exceptions.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
class MessageController (private val messageService: MessageService) {
    @GetMapping("/API/messages/{ticketId}")
    fun getMessagesByTicket(@PathVariable ticketId: Long): List<MessageDTO>? {
        return messageService.getMessagesByTicket(ticketId)
    }

    @PostMapping("/API/messages")
    @ResponseStatus(HttpStatus.CREATED)
    fun postMessage(@RequestBody message: MessageInputDTO,
                    br: BindingResult) {
        if(br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            messageService.postMessage(message)
    }
}