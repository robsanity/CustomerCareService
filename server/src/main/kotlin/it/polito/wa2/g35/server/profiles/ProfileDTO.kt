package it.polito.wa2.g35.server.profiles

data class ProfileDTO(
    val email: String,
    var name: String,
    var surname: String
)

fun Profile.toDTO() : ProfileDTO{
    return ProfileDTO(this.email, this.name, this.surname)
}

fun ProfileDTO.toProfile() : Profile {
    return Profile(this.email, this.name, this.surname)
}