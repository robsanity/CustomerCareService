package it.polito.wa2.g35.server.products

import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name = "product")
class Product {
    @Id
    var id: String = ""
    var name: String = ""

}