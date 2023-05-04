package it.polito.wa2.g35.server.profiles.employee.manager

data class ManagerDTO (
    val id: String?,
    val name: String,
    val surname: String,
    val email: String,
    val managedArea: String
)

fun Manager.toDTO() : ManagerDTO {
    return ManagerDTO(this.id, this.name, this.surname, this.email, this.managedArea)
}