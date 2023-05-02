package it.polito.wa2.g35.server.ticketing.Order

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class OrderController(private val orderService: OrderService) {


    @GetMapping("/API/orders/{customerId}/{productId}")
    fun getOrderByCustomerAndProduct(@PathVariable customerId: String, @PathVariable productId: String): OrderDTO? {
        return orderService.getOrderByCustomerAndProduct(customerId, productId)
    }

    @GetMapping("/API/orders/")
    fun getOrders(): List<OrderDTO>? {
        return orderService.getOrders()
    }

    @GetMapping("/API/orders/{customerId}")
    fun getOrdersByCustomer(customerId: String): List<OrderDTO>?{
        return orderService.getOrdersByCustomer(customerId)
    }
}