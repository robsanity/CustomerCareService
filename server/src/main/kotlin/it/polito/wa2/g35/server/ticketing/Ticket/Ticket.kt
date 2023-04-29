package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.Profile
import it.polito.wa2.g35.server.ticketing.Message.Message
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatus
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Ticket")
class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    var id: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    val creationTimeStamp: Date,

    val issueDescription: String,

    val priority: Int,

    val status: String,

    @ManyToOne
    val idManager: Profile,

    @ManyToOne
    var idProduct: Product,

    @OneToMany(mappedBy = "idTicket")
    val statusHistory: MutableSet<TicketStatus> = mutableSetOf<TicketStatus>(),

    @OneToMany(mappedBy = "idTicket")
    val messages: MutableSet<Message> = mutableSetOf<Message>(),

    )