package com.erictoader.ui.feature.auth.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.erictoader.domain.feature.auth.exception.LoginException
import com.erictoader.domain.feature.auth.exception.RegisterException
import com.erictoader.domain.feature.auth.usecase.abstraction.LoginUseCase
import com.erictoader.domain.feature.auth.usecase.abstraction.RegisterUseCase
import com.erictoader.ui.feature.common.util.DispatcherProvider
import com.erictoader.ui.feature.common.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<AuthEvent>(dispatcherProvider) {

    var state = mutableStateOf<AuthState>(AuthState.Init)
        private set

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Caught exception ${exception.message}")
        when (exception) {
            is LoginException -> state.value = AuthState.ErrorLogin(exception.message)
            is RegisterException -> state.value = AuthState.ErrorRegister(exception.message)
            is Exception -> state.value = AuthState.ErrorUnknown(exception.message ?: "Unknown exception")
        }
    }

    override fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> login(event)
            is AuthEvent.Register -> register(event)
        }
    }

    private fun login(loginEvent: AuthEvent.Login) =
        launchOnIO(exceptionHandler) {
            loginUseCase(loginEvent.username, loginEvent.password)
            state.value = AuthState.LoginSuccessful
        }

    private fun register(registerEvent: AuthEvent.Register) =
        launchOnIO(exceptionHandler) {
            registerUseCase(registerEvent.username, registerEvent.email, registerEvent.password)
            state.value = AuthState.RegisterSuccessful(registerEvent.username)
        }

    private companion object {
        private val TAG = AuthViewModel::class.java.simpleName
    }
}
