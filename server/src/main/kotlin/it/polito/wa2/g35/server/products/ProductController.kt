package it.polito.wa2.g35.server.products

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class ProductController(private val productService: ProductService) {


    @GetMapping("/API/products/")
    fun getAll(): List<ProductDTO> {
        return productService.getAll()
    }

    @GetMapping("/API/products/{productId}")
    fun getProduct(@PathVariable productId: String): ProductDTO? {
        return productService.getProduct(productId)
    }
}