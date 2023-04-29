package it.polito.wa2.g35.server.ticketing.TicketStatus

import it.polito.wa2.g35.server.profiles.Employee.Expert
import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "TicketStatus")
class TicketStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    var id: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    val statusTimestamp: Date,

    val status: String,

    val description: String,

    @ManyToOne
    var idTicket: Ticket,

    @ManyToOne
    var idExpert: Expert,

    )