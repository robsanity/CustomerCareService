package it.polito.wa2.g35.server.ticketing.Order

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.Customer.Customer
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "Order")
class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    var idOrder: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    val orderDate: Date,

    @Temporal(TemporalType.TIMESTAMP)
    val warrantyDuration: Date,

    @ManyToOne
    val idCustomer: Customer,

    @ManyToOne
    val idProduct: Product
)