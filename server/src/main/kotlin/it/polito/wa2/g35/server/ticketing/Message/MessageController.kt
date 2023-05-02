package it.polito.wa2.g35.server.ticketing.Message

import it.polito.wa2.g35.server.ticketing.Order.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController (private val messageService: MessageService) {
    @GetMapping("/API/messages/{ticketId}")
    fun getMessagesByTicket(@PathVariable ticketId: Long): List<MessageDTO>? {
        return messageService.getMessagesByTicket(ticketId.toLong())
    }

    @PostMapping("/API/message")
    fun postMessage(message: MessageDTO?): MessageDTO? {
        return messageService.postMessage(message)
    }
}