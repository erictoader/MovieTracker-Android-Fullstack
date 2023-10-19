package com.erictoader.ui.feature.auth.viewmodel

import com.erictoader.ui.feature.common.viewmodel.State

sealed class AuthState : State {

    object Init : AuthState()

    data class RegisterSuccessful(val usernameAutofill: String): AuthState()

    object LoginSuccessful: AuthState()

    data class ErrorLogin(val message: String): AuthState()

    data class ErrorRegister(val message: String): AuthState()

    data class ErrorUnknown(val message: String): AuthState()
}
