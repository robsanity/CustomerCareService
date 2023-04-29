package it.polito.wa2.g35.server.profiles.Expert

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
@Entity
@Table(name = "Expert")
class Expert (
    @Column(updatable = false, nullable = false)
    @Id
    var id: String? = null,

    val name: String,

    val surname: String,

    val email: String,

    @ManyToOne
    val roleId: Role
)

