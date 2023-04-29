package it.polito.wa2.g35.server.profiles.Customer

import it.polito.wa2.g35.server.exceptions.BadRequestException
import it.polito.wa2.g35.server.exceptions.ProfileNotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult

import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class CustomerController(private val customerService: CustomerService) {
    @GetMapping("/API/profiles/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfile(@PathVariable email: String): CustomerDTO? {
        return customerService.getProfile(email)
    }

    @PostMapping("/API/profiles")
    @ResponseStatus(HttpStatus.CREATED)
    fun postProfile(
        @RequestBody @Valid p: CustomerDTO,
        br: BindingResult
    ) {
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            customerService.postProfile(p)
    }

    @PutMapping("/API/profiles/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun updateProfile(
        @PathVariable("email") email: String,
        @RequestBody @Valid p: CustomerDTO,
        br: BindingResult
    ) {
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            if (email == p.email) {
                customerService.updateProfile(p)
            } else
                throw ProfileNotFoundException("Profile with given email doesn't exists!")
    }
}