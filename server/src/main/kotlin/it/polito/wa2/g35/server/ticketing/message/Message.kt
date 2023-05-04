package it.polito.wa2.g35.server.ticketing.message

import it.polito.wa2.g35.server.ticketing.ticket.Ticket
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Message")
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    var id: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    val messageTimestamp: Date?,

    val messageText: String,

    @ManyToOne(fetch = FetchType.LAZY)
    var ticket: Ticket?,

    var sender: String?
)