package it.polito.wa2.g35.server.profiles.Customer

import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusValues
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, String>