package it.polito.wa2.g35.server.profiles.employee.manager

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.customer.Customer
import java.util.*

data class ManagerDTO (
    val id: String?,
    val name: String,
    val surname: String,
    val email: String,
    val managedArea: String
)
{
    constructor() : this("","","","","")
}

fun Manager.toDTO() : ManagerDTO {
    return ManagerDTO(this.id, this.name, this.surname, this.email, this.managedArea)
}