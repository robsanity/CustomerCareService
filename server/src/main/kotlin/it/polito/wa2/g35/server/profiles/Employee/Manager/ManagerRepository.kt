package it.polito.wa2.g35.server.profiles.Employee.Manager

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ManagerRepository : JpaRepository<Manager, String>