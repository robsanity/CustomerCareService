package it.polito.wa2.g35.server.ticketing.Message

import java.util.*

data class MessageInputDTO (
    val id: Long?,
    val messageTimestamp: Date?,
    val messageText: String,
    val ticket: Long,
    val sender: String
)