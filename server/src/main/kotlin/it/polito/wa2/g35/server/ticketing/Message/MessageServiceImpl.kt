package it.polito.wa2.g35.server.ticketing.Message

import it.polito.wa2.g35.server.exceptions.BadRequestException
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl (private val messageRepository: MessageRepository) : MessageService {

    override fun getMessagesByTicket(ticketid : Long): List<MessageDTO> {
        return messageRepository.getMessagesByTicket(ticketid)?.map { it.toDTO() } ?: emptyList()
    }

    override fun postMessage(message: MessageDTO?): MessageDTO? {
        return if (message != null) {
            messageRepository.save(Message(null, message.messageTimestamp, message.messageText, message.ticket, message.sender, message.attachments)).toDTO()
        } else {
            throw BadRequestException("Message cannot be null")
        }
    }

}