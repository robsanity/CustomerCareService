package it.polito.wa2.g35.server.profiles.Employee.Expert

import it.polito.wa2.g35.server.profiles.Employee.Employee
import jakarta.persistence.Entity
@Entity
class Expert(
    id: String = "",
    name: String = "",
    surname: String = "",
    email: String = "",
    var specialization: String = ""
) : Employee(id, name, surname, email)

