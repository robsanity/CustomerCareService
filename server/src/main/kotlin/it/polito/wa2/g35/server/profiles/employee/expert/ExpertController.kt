package it.polito.wa2.g35.server.profiles.employee.expert

import it.polito.wa2.g35.server.exceptions.BadRequestException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
class ExpertController(private val expertService: ExpertService){
    @GetMapping("/API/experts/{expertId}")
    fun getExpertById(@PathVariable expertId: String?) : ExpertDTO? {
        return expertService.getExpertById(expertId)
    }

    @GetMapping("/API/experts/specialization/{specialization}")
    fun getExpertBySpecialization(@PathVariable specialization: String?) : List<ExpertDTO> {
        return expertService.getExpertBySpecialization(specialization)
    }

    @PostMapping("/API/experts")
    @ResponseStatus(HttpStatus.CREATED)
    fun postExpert(
        @RequestBody @Valid p: ExpertDTO,
        br: BindingResult
    ) : ExpertDTO? {
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            return expertService.createExpert(p)
    }
}