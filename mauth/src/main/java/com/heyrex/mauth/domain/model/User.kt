package com.heyrex.mauth.domain.model

open class User(
    val user: String,
    val password: String,
    val type: Profile,
) {
    enum class Profile(val value: String) {
        CUSTOMER("customer"),
        EMPLOYEE("employee")
    }

    fun toAuthData(): AuthData {
        return AuthData.Builder()
            .user(user)
            .password(password)
            .profile(type.value)
            .build()
    }
}

class Customer(user: String, password: String) : User(user, password, Profile.CUSTOMER)

class Employee(user: String, password: String) : User(user, password, Profile.EMPLOYEE)