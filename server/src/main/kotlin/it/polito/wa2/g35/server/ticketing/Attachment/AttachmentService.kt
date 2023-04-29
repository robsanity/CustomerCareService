package it.polito.wa2.g35.server.ticketing.Attachment

interface AttachmentService {
    fun postMessage(attachment: AttachmentDTO?): AttachmentDTO?

}