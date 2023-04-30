package it.polito.wa2.g35.server.ticketing.Ticket

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository: JpaRepository<Ticket, Long> {
    @Query("SELECT t from Ticket")
}

