package it.polito.wa2.g35.server.profiles

data class ProfileDTO(
    val email: String,
    val name: String,
    val surname: String
)

fun Profile.toDTO() : ProfileDTO{
    return ProfileDTO(this.email, this.name, this.surname)
}