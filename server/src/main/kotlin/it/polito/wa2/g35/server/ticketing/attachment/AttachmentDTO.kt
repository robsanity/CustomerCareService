package it.polito.wa2.g35.server.ticketing.attachment

import it.polito.wa2.g35.server.ticketing.message.Message
import it.polito.wa2.g35.server.ticketing.ticket.Ticket
import java.util.Date

data class AttachmentDTO(
    val id: Long?,
    val message: Message,
    val fileContent : String
) {
    constructor() : this(null,Message(null,null,"",null,""),"")
}

fun Attachment.toDTO() : AttachmentDTO {
    return AttachmentDTO(this.id, this.message, this.fileContent)
}