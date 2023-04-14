package it.polito.wa2.g35.server.profiles

import it.polito.wa2.g35.server.exceptions.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(private val profileRepository: ProfileRepository) : ProfileService {

    override fun getProfile(email: String): ProfileDTO? {
        val profile = profileRepository.findByIdOrNull(email)?.toDTO()
        if(profile != null) {
            return profile
        } else {
            throw ProfileNotFoundException("Profile with given email doesn't exist!")
        }
    }

    override fun postProfile(profile: ProfileDTO?): ProfileDTO? {
        return if (profile != null) {
            val checkIfProfileExists = profileRepository.findByIdOrNull(profile.email)
            if(checkIfProfileExists == null) {
                profileRepository.save(Profile(profile.email, profile.name, profile.surname)).toDTO()
            } else {
                throw DuplicateProfileException("Profile with given email already exists!")
            }
        } else
            null
    }

    override fun updateProfile(profile: ProfileDTO?): ProfileDTO? {
        return if(profile != null) {
            val checkIfProfileExists = profileRepository.findByIdOrNull(profile.email)
            if (checkIfProfileExists != null) {
                profileRepository.save(Profile(profile.email, profile.name, profile.surname)).toDTO()
            } else {
                throw ProfileNotFoundException("Profile with given email doesn't exists!")
            }
        } else {
            null
        }
    }
}