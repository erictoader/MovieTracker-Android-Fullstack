package com.erictoader.data.remote.request

sealed class UserRequest {

    data class Register(
        val email: String,
        val username: String,
        val password: String
    ) : UserRequest()

    data class Update(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String,
    ) : UserRequest()
}
