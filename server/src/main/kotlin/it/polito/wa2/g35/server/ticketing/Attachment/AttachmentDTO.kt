package it.polito.wa2.g35.server.ticketing.Attachment

import it.polito.wa2.g35.server.ticketing.Message.Message

data class AttachmentDTO(
    val id: Long,
    val message: Message,
    val fileContent : String
)