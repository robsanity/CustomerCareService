package it.polito.wa2.g35.server.profiles

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(private val profileRepository: ProfileRepository) : ProfileService {

    override fun getProfile(email: String): ProfileDTO?{
        return profileRepository.findByIdOrNull(email)?.toDTO()
    }

    override fun updateProfile(p: ProfileDTO): ProfileDTO? {
        val updatedProfile = Profile(p.email, p.name, p.surname)
        return profileRepository.save(updatedProfile).toDTO()
    }
}