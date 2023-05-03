package it.polito.wa2.g35.server.ticketing.Message

import it.polito.wa2.g35.server.ticketing.Ticket.TicketNotFoundException
import it.polito.wa2.g35.server.ticketing.Ticket.TicketService
import it.polito.wa2.g35.server.ticketing.Ticket.toTicket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageServiceImpl (private val messageRepository: MessageRepository) : MessageService {
    @Autowired
    lateinit var ticketService: TicketService

    override fun getMessagesByTicket(ticketid : Long): List<MessageDTO> {
        if(ticketService.getTicketById(ticketid) == null)
            throw TicketNotFoundException("Ticket not found with this ID!")
        else
            return messageRepository.getMessagesByTicketId(ticketid).map { it.toDTO() }
    }

    override fun getMessageById(messageId: Long): MessageDTO? {
        return messageRepository.findByIdOrNull(messageId)?.toDTO()
    }

    override fun postMessage(message: MessageInputDTO): MessageDTO? {
        val ticket = ticketService.getTicketById(message.ticket)?.toTicket()
            ?: throw TicketNotFoundException("Ticket not found with this ID!")
        return messageRepository.save(Message(null, Date(), message.messageText, ticket, message.sender)).toDTO()
    }

}