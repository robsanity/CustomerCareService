package it.polito.wa2.g35.server.ticketing.Order

import it.polito.wa2.g35.server.profiles.Customer.CustomerServiceImpl

interface OrderService {
    fun getOrders(): List<OrderDTO>

    //fun getOrderById(id: String): OrderDTO?

    //fun getOrderByProduct(idProduct : String): OrderDTO?

    fun getOrdersByCustomer(idCustomer: String): List<OrderDTO>

    fun getOrderByCustomerAndProduct(idCustomer: String, idProduct: String): OrderDTO?

}