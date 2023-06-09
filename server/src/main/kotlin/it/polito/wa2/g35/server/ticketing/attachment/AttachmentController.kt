package it.polito.wa2.g35.server.ticketing.attachment

import it.polito.wa2.g35.server.exceptions.BadRequestException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class AttachmentController(private val attachmentService: AttachmentService) {

    @GetMapping("API/attachments/message/{messageId}")
    fun getAttachmentsByMessageId(@PathVariable messageId: Long?) : List<AttachmentDTO>? {
        return attachmentService.getAttachmentsByMessageById(messageId)
    }

    @GetMapping("API/attachments/{attachmentId}")
    fun getAttachment(@PathVariable attachmentId: Long?) : AttachmentDTO? {
        return attachmentService.getAttachmentById(attachmentId)
    }

    @PostMapping("API/attachments")
    @ResponseStatus(HttpStatus.CREATED)
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