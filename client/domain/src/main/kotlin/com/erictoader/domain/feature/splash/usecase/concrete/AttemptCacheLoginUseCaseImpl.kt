package com.erictoader.domain.feature.splash.usecase.concrete

import com.erictoader.domain.feature.splash.usecase.abstraction.AttemptCacheLoginUseCase
import com.erictoader.domain.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AttemptCacheLoginUseCaseImpl(
    private val userRepo: UserRepo
) : AttemptCacheLoginUseCase {

    override suspend fun invoke() =
        withContext(Dispatchers.IO) {
            userRepo.attemptCacheLogin()
            Unit
        }
}
