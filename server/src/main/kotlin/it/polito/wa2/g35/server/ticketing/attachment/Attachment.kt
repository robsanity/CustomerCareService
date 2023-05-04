package it.polito.wa2.g35.server.ticketing.attachment

import it.polito.wa2.g35.server.ticketing.message.Message
import jakarta.persistence.*

@Entity
@Table(name = "Attachment")
class Attachment(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val message: Message,

    val fileContent: String
)