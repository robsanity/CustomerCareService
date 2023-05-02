package it.polito.wa2.g35.server.ticketing.Attachment

import it.polito.wa2.g35.server.exceptions.BadRequestException
import it.polito.wa2.g35.server.exceptions.GeneralExceptions
import it.polito.wa2.g35.server.ticketing.Message.toDTO
import it.polito.wa2.g35.server.ticketing.Ticket.toDTO
import org.springframework.stereotype.Service

@Service
class AttachmentServiceImpl(private val attachmentRepository: AttachmentRepository) : AttachmentService {

    override fun getAttachments(attachmentId: Long?): List<AttachmentDTO> {
       return attachmentRepository.getAttachments(attachmentId)?.map { it.toDTO() } ?: emptyList()
    }

    override fun postAttachment(attachment: AttachmentDTO?): AttachmentDTO? {
        return if (attachment != null) {
            attachmentRepository.save(Attachment(null, attachment.message, attachment.fileContent)).toDTO()
        }
        else{
            throw BadRequestException("Bad request format!")
        }
    }
}