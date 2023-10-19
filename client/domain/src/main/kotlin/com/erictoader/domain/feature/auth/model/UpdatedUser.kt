package com.erictoader.domain.feature.auth.model

data class UpdatedUser(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)
