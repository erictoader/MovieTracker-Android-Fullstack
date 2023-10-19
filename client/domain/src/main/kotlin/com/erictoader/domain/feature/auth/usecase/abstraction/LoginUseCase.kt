package com.erictoader.domain.feature.auth.usecase.abstraction

interface LoginUseCase {

    suspend operator fun invoke(username: String, password: String)
}
