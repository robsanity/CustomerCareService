package it.polito.wa2.g35.server.products

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
        return productRepository.findByIdOrNull(productId)?.toDTO()
    }

}