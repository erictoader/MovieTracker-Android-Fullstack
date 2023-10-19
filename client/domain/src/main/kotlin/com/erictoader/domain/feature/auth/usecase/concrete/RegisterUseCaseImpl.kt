package com.erictoader.domain.feature.auth.usecase.concrete

import com.erictoader.domain.feature.auth.exception.RegisterException
import com.erictoader.domain.feature.auth.model.NewUser
import com.erictoader.domain.feature.auth.usecase.abstraction.RegisterUseCase
import com.erictoader.domain.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterUseCaseImpl(
    private val userRepo: UserRepo
) : RegisterUseCase {

    override suspend fun invoke(username: String, email: String, password: String) =
        withContext(Dispatchers.IO) {
            val newUser = NewUser(username, email, password)
            val response = userRepo.registerUser(newUser)
            if (!response.isSuccess) throw RegisterException(response.message)
        }
}
