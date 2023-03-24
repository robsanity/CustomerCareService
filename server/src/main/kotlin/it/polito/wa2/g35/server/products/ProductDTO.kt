package it.polito.wa2.g35.server.products

data class ProductDTO(
    val productId: String,
    val name: String
)

fun Product.toDTO(): ProductDTO {
    return ProductDTO(this.id, this.name)
}
