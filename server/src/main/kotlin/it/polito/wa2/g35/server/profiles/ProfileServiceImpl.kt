package it.polito.wa2.g35.server.profiles

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(private val profileRepository: ProfileRepository) : ProfileService {

    override fun getProfile(email: String): ProfileDTO?{
        return profileRepository.findByIdOrNull(email)?.toDTO()
    }

    override fun postProfile(profile: ProfileDTO?): ProfileDTO? {
        if (profile != null) {
            return profileRepository.save(Profile(profile.email, profile.name, profile.surname)).toDTO()
        } else {
            return  null
        }
    }

    override fun updateProfile(profile: ProfileDTO): ProfileDTO? {
        val checkIfProfileExists = profileRepository.findByIdOrNull(profile.email)
        return if(checkIfProfileExists != null) {
            profileRepository.save(Profile(profile.email, profile.name, profile.surname)).toDTO()
        } else {
            null
        }
    }
}