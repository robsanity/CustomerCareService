package it.polito.wa2.g35.server.ticketing.order

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.customer.Customer
import java.util.Date

data class OrderDTO (
    val id: Long?,
    val customer: Customer,
    val product: Product,
    val date: Date,
    val warrantyDuration: Date
)

fun Order.toDTO() : OrderDTO {
    return OrderDTO(this.id, this.customer, this.product, this.date, this.warrantyDuration)
}
