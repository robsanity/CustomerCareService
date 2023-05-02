package it.polito.wa2.g35.server.ticketing.Attachment

interface AttachmentService {
    fun postAttachment(attachment: AttachmentDTO?): AttachmentDTO?

    fun getAttachments(attachmentId: Long?) : List<AttachmentDTO>?
}