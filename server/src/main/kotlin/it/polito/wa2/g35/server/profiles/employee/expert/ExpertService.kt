package it.polito.wa2.g35.server.profiles.employee.expert

interface ExpertService {
    fun getExpertById(expertId: String?) : ExpertDTO?

    fun getExpertBySpecialization(specialization: String?) : List<ExpertDTO>
}