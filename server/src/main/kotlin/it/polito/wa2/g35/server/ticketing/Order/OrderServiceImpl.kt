package it.polito.wa2.g35.server.ticketing.Order

import it.polito.wa2.g35.server.products.ProductService
import it.polito.wa2.g35.server.profiles.Customer.CustomerService
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class OrderServiceImpl (private val orderRepository: OrderRepository) : OrderService {

    @Autowired
    lateinit var customerService : CustomerService

    @Autowired
    lateinit var productService : ProductService

    override fun getOrders(): List<OrderDTO> {
        return  orderRepository.findAll().map { it.toDTO() }
    }

    override fun getOrdersByCustomer(idCustomer: String): List<OrderDTO> {
        val customer = customerService.getCustomerByEmail( idCustomer )
        val allOrders = orderRepository.findAll().map { it.toDTO() }
        val ordersByCustomer = allOrders.filter { it.customer.email == idCustomer }

        if(ordersByCustomer.isNotEmpty())
            return ordersByCustomer
        else
            return emptyList()
    }


    override fun getOrderByCustomerAndProduct(idCustomer: String, idProduct: String): OrderDTO? {
        /*
            val customer = customerService.getCustomer( idCustomer )
            val product = productService.getProduct(idProduct)
        */
        return orderRepository.getOrdersByCustomerAndProduct(idCustomer, idProduct)?.toDTO()

        /*val allOrders = orderRepository.findAll().map { it.toDTO() }
        orderRepository.findBy()
        try {
            val orderByCustomerAndProduct = allOrders.map {
                it.customer.toString() == idCustomer
                        && it.product.toString() == idProduct
            }
            return orderByCustomerAndProduct
        } catch ( e: IllegalArgumentException) {
            return null
        } */

    }
}