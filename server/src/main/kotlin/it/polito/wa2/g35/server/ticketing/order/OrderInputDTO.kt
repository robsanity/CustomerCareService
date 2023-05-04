package it.polito.wa2.g35.server.ticketing.order

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.customer.Customer
import java.util.Date

data class OrderInputDTO (
    val id: Long?,
    val customerId: String,
    val productId: String,
    val date: Date,
    val warrantyDuration: Date
)

