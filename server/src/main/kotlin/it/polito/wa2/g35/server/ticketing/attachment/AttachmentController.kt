package it.polito.wa2.g35.server.ticketing.attachment

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import jakarta.validation.Valid
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import it.polito.wa2.g35.server.exceptions.BadRequestException

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class AttachmentController(private val attachmentService: AttachmentService) {

    @GetMapping("API/attachments/{messageId}")
    fun getAttachmentsByMessageId(@PathVariable messageId: Long?) : List<AttachmentDTO>? {
        return attachmentService.getAttachmentsByMessageById(messageId)
    }

    @GetMapping("API/attachment/{attachmentId}")
    fun getAttachment(@PathVariable attachmentId: Long?) : AttachmentDTO? {
        return attachmentService.getAttachmentById(attachmentId)
    }

    @PostMapping("API/attachments")
    fun postAttachment(
        @RequestBody @Valid p: AttachmentInputDTO,
        br: BindingResult
    ){
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            attachmentService.postAttachment(p)
    }
}