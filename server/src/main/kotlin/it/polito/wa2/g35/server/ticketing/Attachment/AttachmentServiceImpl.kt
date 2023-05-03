package it.polito.wa2.g35.server.ticketing.Attachment

import it.polito.wa2.g35.server.exceptions.BadRequestException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AttachmentServiceImpl(private val attachmentRepository: AttachmentRepository) : AttachmentService {

    override fun getAttachmentsByMessageById(messageId: Long?): List<AttachmentDTO> {
        if (messageId != null) {
             return attachmentRepository.getAttachmentsByMessageId(messageId).map { it.toDTO() }
        } else {
            return emptyList()
        }
    }

    override fun getAttachmentById(attachmentId: Long?): AttachmentDTO? {
        return attachmentRepository.findByIdOrNull(attachmentId)?.toDTO()
    }

    override fun postAttachment(attachment: AttachmentDTO?): AttachmentDTO? {
        return if (attachment != null) {
            attachmentRepository.save(Attachment(null, attachment.message, attachment.fileContent)).toDTO()
        } else {
            throw BadRequestException("Bad request format!")
        }
    }
}