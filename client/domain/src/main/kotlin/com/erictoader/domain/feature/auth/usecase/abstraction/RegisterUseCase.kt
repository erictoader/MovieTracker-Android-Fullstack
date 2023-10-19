package com.erictoader.domain.feature.auth.usecase.abstraction

interface RegisterUseCase {

    suspend operator fun invoke(username: String, email: String, password: String)
}
