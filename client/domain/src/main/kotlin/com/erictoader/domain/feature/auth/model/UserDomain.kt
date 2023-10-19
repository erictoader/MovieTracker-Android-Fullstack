package com.erictoader.domain.feature.auth.model

import com.erictoader.domain.feature.common.DomainModel

data class UserDomain(
    val id: Int,
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = ""
) : DomainModel
