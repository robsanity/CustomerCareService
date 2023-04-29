package it.polito.wa2.g35.server.profiles.Expert.Role

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Role")
class Role (
    @Id
    @Column(updatable = false, nullable = false)
    var roleId: Int,

    val description: String
)
