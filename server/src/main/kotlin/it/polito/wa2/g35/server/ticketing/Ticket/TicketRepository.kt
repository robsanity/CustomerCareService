package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.profiles.Employee.Expert.Expert
import it.polito.wa2.g35.server.ticketing.Order.Order
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusValues
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository: JpaRepository<Ticket, Long> {

     fun getTicketsByStatus(status: TicketStatusValues) : List<Ticket>?

     fun getTicketsByExpert(idExpert: String) : List<Ticket>?

     fun getTicketsByPriority(priority: TicketPriority) : List<Ticket>?

     fun getTicketsByCustomer(idCustomer: String) : List<Ticket>?

}