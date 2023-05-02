package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.ticketing.Order.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository: JpaRepository<Ticket, Long> {
    @Query("SELECT t from Ticket t WHERE t.id =: idTicket")
    fun getTicketById(idTicket: String): Ticket?

    //@Query("SELECT t FROM Order t WHERE t.customer.email = :idCustomer AND t.product.id = :idProduct")
    //fun getOrdersByCustomerAndProduct(idCustomer: String, idProduct: String): Order?
     fun
}

