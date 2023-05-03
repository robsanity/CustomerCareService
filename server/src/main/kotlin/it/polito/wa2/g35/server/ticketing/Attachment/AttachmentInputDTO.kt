package it.polito.wa2.g35.server.ticketing.Attachment

data class AttachmentInputDTO(
    val id: Long?,
    val messageId: Long,
    val fileContent : String
)