package it.polito.wa2.g35.server.ticketing.order

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class OrderController(private val orderService: OrderService) {
    @GetMapping("/API/orders/{customerId}/{productId}")
    fun getOrderByCustomerAndProduct(@PathVariable customerId: String, @PathVariable productId: String): OrderDTO? {
        return orderService.getOrderByCustomerAndProduct(customerId, productId)
    }

    @GetMapping("/API/orders")
    fun getOrders(): List<OrderDTO>? {
        return orderService.getOrders()
    }

    @GetMapping("/API/orders/{customerId}")
    fun getOrdersByCustomer(@PathVariable customerId: String): List<OrderDTO>?{
        return orderService.getOrdersByCustomer(customerId)
    }

    @PostMapping("/API/orders/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody order: OrderInputDTO): OrderDTO? {
        return orderService.createOrder(order)
    }
}