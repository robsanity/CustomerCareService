package it.polito.wa2.g35.server.products

import it.polito.wa2.g35.server.profiles.DuplicateProfileException
import it.polito.wa2.g35.server.profiles.customer.Customer
import it.polito.wa2.g35.server.profiles.customer.CustomerDTO
import it.polito.wa2.g35.server.profiles.customer.toDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service // responsible for the business logic
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {
    override fun getAll(): List<ProductDTO> {
        return productRepository.findAll().map { it.toDTO() }
    }

    override fun getProductById(productId: String): ProductDTO? {
        val product = productRepository.findByIdOrNull(productId)?.toDTO()
        if(product != null)
            return product
        else
            throw ProductNotFoundException("Product not found with this product id!")
    }

    override fun postProduct(product: ProductDTO?): ProductDTO? {
        return if (product != null) {
            val checkIfProductExsist = productRepository.findByIdOrNull(product.id)
            if(checkIfProductExsist == null) {
                productRepository.save(Product(product.id, product.name)).toDTO()
            } else {
                throw DuplicateProfileException("Profile with given email already exists!")
            }
        } else
            null
    }
}