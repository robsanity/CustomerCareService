package it.polito.wa2.g35.server.products

import it.polito.wa2.g35.server.exceptions.BadRequestException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class ProductController(private val productService: ProductService) {
    @GetMapping("/API/products/")
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<ProductDTO> {
        return productService.getAll()
    }

    @GetMapping("/API/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    fun getProduct(@PathVariable productId: String): ProductDTO? {
        return productService.getProductById(productId)
    }

    @PostMapping("/API/products")
    @ResponseStatus(HttpStatus.CREATED)
    fun postProduct(
        @RequestBody @Valid p: ProductDTO,
        br: BindingResult
    ) {
        if (br.hasErrors())
            throw BadRequestException("Bad request format!")
        else
            productService.createProduct(p)
    }
}