package it.polito.wa2.g35.server.ticketing.Order

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.Customer.Customer
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "Orders")
class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    var id: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    val Date: Date,

    @Temporal(TemporalType.TIMESTAMP)
    val warrantyDuration: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    val customer: Customer,

    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product
)