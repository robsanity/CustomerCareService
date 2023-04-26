package it.polito.wa2.g35.server.profiles

import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

/*Automatically mapped to database table */
@Entity
@Table(name = "users")
class Profile(email: String, name: String, surname: String) {
    @Id
    var email: String = email
    var name: String = name
    var surname: String = surname

    @OneToMany(mappedBy = "idManager")
    val tickets = mutableSetOf<Ticket>()

    var role: String = ""
}