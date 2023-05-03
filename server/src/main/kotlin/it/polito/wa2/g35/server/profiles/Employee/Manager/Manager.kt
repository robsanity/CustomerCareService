package it.polito.wa2.g35.server.profiles.Employee.Manager

import it.polito.wa2.g35.server.profiles.Employee.Employee
import jakarta.persistence.Entity
@Entity
class Manager(
    id: String? = "",
    name: String = "",
    surname: String = "",
    email: String = "",
    var managedArea: String = ""
) : Employee(id, name, surname, email)