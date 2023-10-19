package com.erictoader.ui.feature.auth.viewmodel

import com.erictoader.ui.feature.common.viewmodel.Event

sealed class AuthEvent : Event {

    data class Login(
        val username: String,
        val password: String
    ) : AuthEvent()

    data class Register(
        val username: String,
        val email: String,
        val password: String
    ) : AuthEvent()
}
