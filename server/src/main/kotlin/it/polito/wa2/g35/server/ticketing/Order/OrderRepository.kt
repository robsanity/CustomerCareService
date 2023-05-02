package it.polito.wa2.g35.server.ticketing.Order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long> {

    @Query("SELECT t FROM Order t WHERE t.customer = :customer AND t.product = :product")
    fun getOrdersByCustomerAndProduct(idCustomer: String, idProduct: String): OrderDTO?

}