package it.polito.wa2.g35.server.ticketing.Message

import it.polito.wa2.g35.server.exceptions.BadRequestException
import org.springframework.stereotype.Service
import java.util.Date

@Service
class MessageServiceImpl (private val messageRepository: MessageRepository) : MessageService {

    override fun getMessagesByTicket(ticketid : Long): List<MessageDTO> {
        return messageRepository.getMessagesByTicketId(ticketid)?.map { it.toDTO() } ?: throw BadRequestException("Ticket not found")
    }

    override fun postMessage(message: MessageDTO?): MessageDTO? {
        return if (message != null) {
            messageRepository.save(Message(null, Date(), message.messageText, null, message.sender, null)).toDTO()
        } else {
            throw BadRequestException("Message cannot be null")
        }
    }

}