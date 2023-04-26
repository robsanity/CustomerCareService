package it.polito.wa2.g35.server.ticketing.Message

import it.polito.wa2.g35.server.profiles.Profile
import it.polito.wa2.g35.server.ticketing.Attachment.Attachment
import it.polito.wa2.g35.server.ticketing.Ticket.Ticket
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
    val statusTimestamp: Date,

    val messageText: String,

    @ManyToOne
    var idTicket: Ticket,

    @ManyToOne
    var idSender: Profile,

    @OneToMany(mappedBy = "idMessage")
    val attachments: MutableSet<Attachment> = mutableSetOf()
)