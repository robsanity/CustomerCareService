package it.polito.wa2.g35.server.ticketing.Message

interface MessageService {
    fun getMessagesByTicket(ticketid : Long): List<MessageDTO>

    fun postMessage(message: MessageDTO?): MessageDTO?
}