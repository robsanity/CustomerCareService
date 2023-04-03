package it.polito.wa2.g35.server.profiles

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(private val profileRepository: ProfileRepository) : ProfileService {

    override fun getProfile(email: String): ProfileDTO?{
        return profileRepository.findByIdOrNull(email)?.toDTO()
    }

    override fun postProfile(profile: ProfileDTO): ProfileDTO? {
        val newProfile = Profile(profile.email,profile.name,profile.surname)
        return profileRepository.save(newProfile).toDTO()
    }

    override fun updateProfile(profile: ProfileDTO): ProfileDTO? {
        val updatedProfile = Profile(profile.email, profile.name, profile.surname)
        return profileRepository.save(updatedProfile).toDTO()
    }
}