package it.polito.wa2.g35.server.profiles.Employee.Expert


data class ExpertDTO (
    val id: String?,
    val name: String,
    val surname: String,
    val email: String,
    val specialization: String
    )

fun Expert.toDTO() : ExpertDTO {
    return ExpertDTO(this.id, this.name, this.surname, this.email, this.specialization)
}
