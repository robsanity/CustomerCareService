package it.polito.wa2.g35.server.ticketing.message

import it.polito.wa2.g35.server.exceptions.BadRequestException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController (private val messageService: MessageService) {
    @GetMapping("/API/messages/{ticketId}")
    fun getMessagesByTicket(@PathVariable ticketId: Long): List<MessageDTO>? {
        return messageService.getMessagesByTicket(ticketId)
    }

    @PostMapping("/API/messages")
    fun postMessage(@RequestBody message: MessageInputDTO,
                    br: BindingResult): MessageDTO? {
        if(br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            return messageService.postMessage(message)
    }
}