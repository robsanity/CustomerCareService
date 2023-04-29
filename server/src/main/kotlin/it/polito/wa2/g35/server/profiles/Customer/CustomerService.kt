package it.polito.wa2.g35.server.profiles.Customer

interface CustomerService {
    fun getCustomer(email: String) : CustomerDTO?

    fun postCustomer(profile: CustomerDTO?) : CustomerDTO?

    fun updateCustomer(profile: CustomerDTO?) : CustomerDTO?
}