package com.erictoader.domain.feature.auth.model

data class NewUser(
    val username: String,
    val email: String,
    val password: String,
)
