package it.polito.wa2.g35.server.ticketing.Attachment

import it.polito.wa2.g35.server.ticketing.Message.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query
@Repository
interface AttachmentRepository: JpaRepository<Attachment, Long>{

    fun getAttachmentsByMessageId(messageId: Long) : List<Attachment>
}