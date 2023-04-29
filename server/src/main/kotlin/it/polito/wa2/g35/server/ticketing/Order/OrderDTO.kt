package it.polito.wa2.g35.server.ticketing.Order

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.Customer.Customer
import java.util.Date

data class OrderDTO (
    val id: String,
    val customer: Customer,
    val product: Product,
    val date: Date,
    val warrantyDuration: Date
)

