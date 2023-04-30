package it.polito.wa2.g35.server.ticketing.Message

import it.polito.wa2.g35.server.ticketing.Attachment.Attachment
import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
import java.util.Date

data class MessageDTO(
    val id: Long,
    val messageTimestamp: Date,
    val messageText: String,
    val ticket: Ticket,
    val sender: String,
    val attachments: MutableSet<Attachment> = mutableSetOf()
)




