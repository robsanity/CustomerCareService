package it.polito.wa2.g35.server.profiles.Employee.Expert

interface ExpertService {
    fun getExpertById(expertId: String?) : ExpertDTO?
}