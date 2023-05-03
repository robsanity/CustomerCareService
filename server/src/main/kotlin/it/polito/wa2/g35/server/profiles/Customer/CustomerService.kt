package it.polito.wa2.g35.server.profiles.Customer

interface CustomerService {
    fun getCustomerByEmail(email: String) : CustomerDTO?

    fun postCustomer(profile: CustomerDTO?) : CustomerDTO?

    fun updateCustomer(profile: CustomerDTO?) : CustomerDTO?
}