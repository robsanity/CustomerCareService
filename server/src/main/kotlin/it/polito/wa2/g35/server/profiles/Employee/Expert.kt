package it.polito.wa2.g35.server.profiles.Employee

import jakarta.persistence.Entity
@Entity
class Expert(
    id: String? = null,
    name: String? = null,
    surname: String? = null,
    email: String? = null,
    var specialization: String? = null
) : Employee(id, name, surname, email)

