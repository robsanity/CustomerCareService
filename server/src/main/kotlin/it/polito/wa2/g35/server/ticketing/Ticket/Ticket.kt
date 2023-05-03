package it.polito.wa2.g35.server.ticketing.Ticket

import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.profiles.Customer.Customer
import it.polito.wa2.g35.server.profiles.Employee.Expert.Expert
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatus
import it.polito.wa2.g35.server.ticketing.TicketStatus.TicketStatusValues
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

    @Enumerated(EnumType.STRING)
    val priority: TicketPriority,

    @Enumerated(EnumType.STRING)
    val status: TicketStatusValues,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    val expert: Expert,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product,

    @ManyToOne(fetch = FetchType.LAZY)
    var customer: Customer,

    @OneToMany(mappedBy = "ticket")
    val statusHistory: MutableSet<TicketStatus> = mutableSetOf<TicketStatus>(),
    )
