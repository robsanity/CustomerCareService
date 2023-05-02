package it.polito.wa2.g35.server.ticketing.Attachment

import org.springframework.stereotype.Service

@Service
class AttachmentServiceImpl(private val attachmentRepository: AttachmentRepository) : AttachmentService {
    override fun getAttachment(attachmentId: String): AttachmentDTO? {
        TODO("Not yet implemented")
    }

    override fun postAttachment(attachment: AttachmentDTO?): AttachmentDTO? {
        TODO("Not yet implemented")
    }
}