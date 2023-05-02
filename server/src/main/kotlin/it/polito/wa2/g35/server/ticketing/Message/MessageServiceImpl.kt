package it.polito.wa2.g35.server.ticketing.Message

import org.springframework.stereotype.Service
import it.polito.wa2.g35.server.ticketing.Message.MessageService

@Service
class MessageServiceImpl (private val messageRepository: MessageRepository) : MessageService {

    override fun getMessagesByTicket(ticketid : Long): List<MessageDTO> {
        return messageRepository.getMessagesByTicket(ticketid)?.map { it.toDTO() } ?: emptyList()
    }

    override fun postMessage(message: MessageDTO?): MessageDTO? {
        return if (message != null) {
            messageRepository.save(Message(null, message.ticket, message.author, message.content, message.date)).toDTO()
        } else {
            throw IllegalArgumentException("Message cannot be null")
        }
    }

}