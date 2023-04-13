package it.polito.wa2.g35.server.profiles

import org.jetbrains.annotations.NotNull

data class ProfileDTO(
    @field:NotNull
    val email: String,
    @field:NotNull
    var name: String,
    @field:NotNull
    var surname: String
)

fun Profile.toDTO() : ProfileDTO{
    return ProfileDTO(this.email, this.name, this.surname)
}

fun ProfileDTO.toProfile() : Profile {
    return Profile(this.email, this.name, this.surname)
}