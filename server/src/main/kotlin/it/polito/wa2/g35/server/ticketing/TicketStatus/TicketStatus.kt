package it.polito.wa2.g35.server.ticketing.TicketStatus

import it.polito.wa2.g35.server.profiles.Employee.Expert.Expert
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
    val statusTimestamp: Date?,

    @Enumerated(EnumType.STRING)
    val status: TicketStatusValues,

    val description: String?,

    @ManyToOne
    var ticket: Ticket,

    @ManyToOne
    var expert: Expert,

    )

enum class TicketStatusValues {
    OPEN,
    IN_PROGRESS,
    CLOSED,
    RESOLVED,
    REOPENED
}