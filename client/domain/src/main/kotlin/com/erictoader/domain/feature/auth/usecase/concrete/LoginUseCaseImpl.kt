package com.erictoader.domain.feature.auth.usecase.concrete

import com.erictoader.domain.feature.auth.exception.LoginException
import com.erictoader.domain.feature.auth.usecase.abstraction.LoginUseCase
import com.erictoader.domain.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCaseImpl(
    private val userRepo: UserRepo
) : LoginUseCase {

    override suspend fun invoke(username: String, password: String) =
        withContext(Dispatchers.IO) {
            val response = userRepo.loginUser(username, password)
            if (!response.isSuccess) throw LoginException(response.message)
        }
}
