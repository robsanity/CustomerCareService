package it.polito.wa2.g35.server.ticketing.Attachment

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import it.polito.wa2.g35.server.exceptions.BadRequestException

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class AttachmentController(private val attachmentService: AttachmentService) {

    @GetMapping("API/attachment/{idAttachment}")
    fun getAttachment(attachmentId: Long?) : List<AttachmentDTO>? {
        return attachmentService.getAttachments(attachmentId)
    }

    @PostMapping("API/attachment")
    fun postAttachment(
        @RequestBody @Valid p: AttachmentDTO,
        br: BindingResult
    ){
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            attachmentService.postAttachment(p)
    }
}