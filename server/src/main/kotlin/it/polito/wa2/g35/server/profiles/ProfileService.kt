package it.polito.wa2.g35.server.profiles

interface ProfileService {
    fun getProfile(email: String) : ProfileDTO?

    fun postProfile(){}

    fun putProfile(){}
}