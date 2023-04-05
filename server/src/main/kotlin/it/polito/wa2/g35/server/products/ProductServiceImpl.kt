package it.polito.wa2.g35.server.products

import it.polito.wa2.g35.server.exceptions.ProductNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service // responsible for the business logic
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {
    override fun getAll(): List<ProductDTO> {
        return productRepository.findAll().map { it.toDTO() }
    }

    override fun getProduct(productId: String): ProductDTO? {
        val product = productRepository.findByIdOrNull(productId)?.toDTO()
        if(product != null)
            return product
        else
            throw ProductNotFoundException("Product not found with this product id!")
    }

}