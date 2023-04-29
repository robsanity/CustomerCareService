package it.polito.wa2.g35.server.ticketing.Order

import jakarta.persistence.*

@Entity
@Table(name = "Order")
class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    var id: Long? = null,


)