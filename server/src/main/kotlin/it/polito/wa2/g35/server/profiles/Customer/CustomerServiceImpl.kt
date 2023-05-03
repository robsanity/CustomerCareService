package it.polito.wa2.g35.server.profiles.Customer

import it.polito.wa2.g35.server.exceptions.*
import it.polito.wa2.g35.server.profiles.DuplicateProfileException
import it.polito.wa2.g35.server.profiles.ProfileNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val profileRepository: CustomerRepository) : CustomerService {

    override fun getCustomer(email: String): Customer? {
        val profile = profileRepository.findByIdOrNull(email)
        if(profile != null) {
            return profile
        } else {
            throw ProfileNotFoundException("Profile with given email doesn't exist!")
        }
    }

    override fun postCustomer(profile: CustomerDTO?): CustomerDTO? {
        return if (profile != null) {
            val checkIfProfileExists = profileRepository.findByIdOrNull(profile.email)
            if(checkIfProfileExists == null) {
                profileRepository.save(Customer(profile.email, profile.name, profile.surname)).toDTO()
            } else {
                throw DuplicateProfileException("Profile with given email already exists!")
            }
        } else
            null
    }

    override fun updateCustomer(profile: CustomerDTO?): CustomerDTO? {
        return if(profile != null) {
            val checkIfProfileExists = profileRepository.findByIdOrNull(profile.email)
            if (checkIfProfileExists != null) {
                profileRepository.save(Customer(profile.email, profile.name, profile.surname)).toDTO()
            } else {
                throw ProfileNotFoundException("Profile with given email doesn't exists!")
            }
        } else {
            null
        }
    }
}