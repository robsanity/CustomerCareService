package it.polito.wa2.g35.server.profiles.Customer

import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

/*Automatically mapped to database table */
@Entity
@Table(name = "users")
class Customer(@Id var email: String, var name: String, var surname: String) {
    @OneToMany(mappedBy = "idManager")
    val tickets = mutableSetOf<Ticket>()
}