package it.polito.wa2.g35.server.products

import it.polito.wa2.g35.server.profiles.customer.Customer
import java.util.*

data class ProductDTO(
    val id: String,
    val name: String
)
{
    constructor() : this("","")
}

fun Product.toDTO(): ProductDTO {
    return ProductDTO(this.id, this.name)
}

fun ProductDTO.toProduct(): Product {
    return Product(this.id, this.name)
}
