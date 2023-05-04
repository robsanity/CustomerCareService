package it.polito.wa2.g35.server.ticketing.attachment

import it.polito.wa2.g35.server.ticketing.message.MessageNotFoundException
import it.polito.wa2.g35.server.ticketing.message.MessageService
import it.polito.wa2.g35.server.ticketing.message.toMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AttachmentServiceImpl(private val attachmentRepository: AttachmentRepository) : AttachmentService {

    @Autowired
    lateinit var messageService: MessageService

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

    override fun postAttachment(attachment: AttachmentInputDTO): AttachmentDTO? {
        val message = messageService.getMessageById(attachment.messageId)?.toMessage()
            ?: throw MessageNotFoundException("Message not found with this ID!")
        return attachmentRepository.save(Attachment(null, message, attachment.fileContent)).toDTO()
    }
}