package it.polito.wa2.g35.server.profiles.employee.expert

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
}