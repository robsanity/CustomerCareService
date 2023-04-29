package it.polito.wa2.g35.server.profiles.Expert

import it.polito.wa2.g35.server.profiles.Employee.Expert
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpertRepository: JpaRepository<Expert, String>