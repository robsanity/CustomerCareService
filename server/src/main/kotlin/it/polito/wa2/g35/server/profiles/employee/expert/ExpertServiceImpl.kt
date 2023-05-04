package it.polito.wa2.g35.server.profiles.employee.expert

import it.polito.wa2.g35.server.profiles.DuplicateProfileException
import it.polito.wa2.g35.server.profiles.customer.Customer
import it.polito.wa2.g35.server.profiles.customer.toDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExpertServiceImpl(private val expertRepository: ExpertRepository) : ExpertService {
    override fun getExpertById(expertId: String?) : ExpertDTO? {
        return expertRepository.findByIdOrNull(expertId)?.toDTO()
    }

    override fun getExpertBySpecialization(specialization: String?): List<ExpertDTO> {
        return expertRepository.findBySpecialization(specialization).map { it.toDTO() }
    }

    override fun createExpert(expert: ExpertDTO): ExpertDTO? {
        return if (expert != null) {
            val checkIfProfileExists = expertRepository.findByIdOrNull(expert.id)
            if(checkIfProfileExists == null) {
                expertRepository.save(Expert(expert.id, expert.name, expert.surname, expert.email,expert.specialization)).toDTO()
            } else {
                throw DuplicateProfileException("Profile with given email already exists!")
            }
        } else
            null
    }
}