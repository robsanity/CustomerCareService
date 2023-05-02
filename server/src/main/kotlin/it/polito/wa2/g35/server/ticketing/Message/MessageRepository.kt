package it.polito.wa2.g35.server.ticketing.Message;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query

@Repository
interface MessageRepository: JpaRepository<Message, Long> {

    @Query("SELECT m from Message m WHERE m.ticket.id =: ticketid")
    fun getMessagesByTicket(ticketid : Long): List<Message>?


}