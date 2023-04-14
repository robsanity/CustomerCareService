package it.polito.wa2.g35.server.profiles

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class ProfileDTO(
    @field:Email(regexp = ".*"+"@"+".*"+"\\."+".*") @Size(min=4,max=40) @NotBlank
    val email: String,
    @field:Size(min=2,max=25) @NotBlank
    var name: String,
    @field:Size(min=2,max=25) @NotBlank
    var surname: String
)

fun Profile.toDTO() : ProfileDTO{
    return ProfileDTO(this.email, this.name, this.surname)
}

fun ProfileDTO.toProfile() : Profile {
    return Profile(this.email, this.name, this.surname)
}