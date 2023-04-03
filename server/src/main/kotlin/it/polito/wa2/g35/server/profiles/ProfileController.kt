package it.polito.wa2.g35.server.profiles

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ProfileController(private val profileService: ProfileService) {

    @GetMapping("/profiles/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun getProfile(@PathVariable email: String) : ProfileDTO? {
        return profileService.getProfile(email)
    }

    @PostMapping("/profiles")
    @ResponseStatus(HttpStatus.CREATED)
    fun postProfile(@RequestBody p: ProfileDTO?){
        if (p != null){
            profileService.postProfile(p)
        }
    }

    @PutMapping("/profiles/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun updateProfile(@PathVariable("email") email: String, @RequestBody p: ProfileDTO?) {
        if (p != null && email == p.email) { profileService.updateProfile(p) }
    }
}