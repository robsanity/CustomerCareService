package it.polito.wa2.g35.server.ticketing.Attachment

interface AttachmentService {
    fun postAttachment(attachment: AttachmentInputDTO): AttachmentDTO?

    fun getAttachmentsByMessageById(messageId: Long?) : List<AttachmentDTO>

    fun getAttachmentById(attachmentId: Long?): AttachmentDTO?
}