package it.polito.wa2.g35.server.ticketing.order

import it.polito.wa2.g35.server.products.ProductNotFoundException
import it.polito.wa2.g35.server.products.ProductService
import it.polito.wa2.g35.server.products.toProduct
import it.polito.wa2.g35.server.profiles.ProfileNotFoundException
import it.polito.wa2.g35.server.profiles.customer.CustomerService
import it.polito.wa2.g35.server.profiles.customer.toCustomer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository) : OrderService {

    @Autowired
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var productService: ProductService

    override fun getOrders(): List<OrderDTO> {
        return orderRepository.findAll().map { it.toDTO() }
    }

    override fun getOrdersByCustomer(idCustomer: String): List<OrderDTO> {
        val customer = customerService.getCustomerByEmail(idCustomer)
        val allOrders = orderRepository.findAll().map { it.toDTO() }
        val ordersByCustomer = allOrders.filter { it.customer.email == idCustomer }

        return if (ordersByCustomer.isNotEmpty())
            ordersByCustomer
        else
            emptyList()
    }

    override fun getOrderByCustomerAndProduct(idCustomer: String, idProduct: String): OrderDTO? {
        return orderRepository.getOrdersByCustomerAndProduct(idCustomer, idProduct)?.toDTO()
    }

    override fun createOrder(order: OrderInputDTO): OrderDTO? {
        val customer = customerService.getCustomerByEmail(order.customerId) ?: throw ProfileNotFoundException("Profile not found with this id!")
        val product = productService.getProductById(order.productId) ?: throw ProductNotFoundException("Product not found with this id!")

        return orderRepository.save(
            Order(
                null,
                order.date,
                order.warrantyDuration,
                customer.toCustomer(),
                product.toProduct()
            )
        ).toDTO()
    }

}