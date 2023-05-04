package it.polito.wa2.g35.server.profiles.employee.expert

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

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
}