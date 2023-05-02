package it.polito.wa2.g35.server.ticketing.Order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface OrderRepository: JpaRepository<Order, Long> {

    /*
    @Query("SELECT t FROM Order t WHERE t.customer.email = :idCustomer AND t.product.id = :idProduct")
    fun getOrderByCustomerAndProduct(idCustomer: String, idProduct: String): Order?

    @Query("SELECT t FROM Order t WHERE t.customer.email = :idCustomer")
    fun getOrdersByCustomer(idCustomer: String): List<Order>

    @Query("SELECT t FROM Order t WHERE t.product.id = :idProduct")
    fun getOrderByProduct(idProduct : String): Order?

    @Query("SELECT t FROM Order t")
    fun getOrders(): List<Order>
*/
    @Query("SELECT t FROM Order t WHERE t.customer.email = :idCustomer AND t.product.id = :idProduct")
    fun getOrdersByCustomerAndProduct(idCustomer: String, idProduct: String): Order?

}