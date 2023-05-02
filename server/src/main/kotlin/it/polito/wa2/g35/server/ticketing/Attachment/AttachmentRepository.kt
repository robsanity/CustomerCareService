package it.polito.wa2.g35.server.ticketing.Attachment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query
@Repository
interface AttachmentRepository: JpaRepository<Attachment, Long>{

    @Query("SELECT a from Attachment a WHERE a.id =:attachmentId")
    fun getAttachments(attachmentId: Long?) : List<Attachment>?

    fun postAttachment()

}