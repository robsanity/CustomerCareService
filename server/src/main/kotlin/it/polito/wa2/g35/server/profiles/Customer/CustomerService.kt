package it.polito.wa2.g35.server.profiles.Customer

interface CustomerService {
    fun getProfile(email: String) : CustomerDTO?

    fun postProfile(profile: CustomerDTO?) : CustomerDTO?

    fun updateProfile(profile: CustomerDTO?) : CustomerDTO?
}