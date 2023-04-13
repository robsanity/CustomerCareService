package it.polito.wa2.g35.server.profiles

import it.polito.wa2.g35.server.exceptions.ProfileNotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError

import org.springframework.web.bind.annotation.*
import javax.naming.Binding

@RestController

class ProfileController(private val profileService: ProfileService) {

    @GetMapping("/profiles/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfile(@PathVariable  email: String) : ProfileDTO? {
        return profileService.getProfile(email)
    }

    @PostMapping("/profiles")
    @ResponseStatus(HttpStatus.CREATED)
    fun postProfile(@RequestBody @Valid p: ProfileDTO,
                    br: BindingResult): String {
        if (br.hasErrors())
            return "form"
        else
            profileService.postProfile(p)
            return "redirect:formResult";
    }



    @PutMapping("/profiles/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun updateProfile(@PathVariable("email") email: String, @RequestBody @Valid p: ProfileDTO, br: BindingResult) : String{
        if (br.hasErrors())
            return "form"
        else
        if (email == p.email) {
            profileService.updateProfile(p)
            return "redirect:formResult";
             }
        else
            throw ProfileNotFoundException("Profile with given email doesn't exists!")
    }

}