package it.polito.wa2.g35.server.profiles.Employee.Expert

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExpertServiceImpl(private val expertRepository: ExpertRepository) : ExpertService {
    override fun getExpertById(expertId: String?) : ExpertDTO? {
        return expertRepository.findByIdOrNull(expertId)?.toDTO()
    }
}