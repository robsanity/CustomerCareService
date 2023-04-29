package it.polito.wa2.g35.server.profiles.Employee

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open class Employee (
    @Column(updatable = false, nullable = false)
    @Id
    var id: String? = null,
    val name: String?,
    val surname: String?,
    val email: String?
)