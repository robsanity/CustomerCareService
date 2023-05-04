package it.polito.wa2.g35.server.products

interface ProductService {
    fun getAll() : List<ProductDTO>
    fun getProductById(productId: String) : ProductDTO?
    fun createProduct(product: ProductDTO?) : ProductDTO?

}