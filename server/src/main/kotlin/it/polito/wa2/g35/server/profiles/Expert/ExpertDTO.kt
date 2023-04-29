package it.polito.wa2.g35.server.profiles.Expert

import it.polito.wa2.g35.server.profiles.Expert.Role.Role

data class ExpertDTO (
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val role: Role
    )
