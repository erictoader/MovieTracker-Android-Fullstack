package com.erictoader.domain.repo

import com.erictoader.domain.feature.auth.model.NewUser
import com.erictoader.domain.feature.auth.model.UpdatedUser
import com.erictoader.domain.feature.auth.model.UserDomain
import com.erictoader.domain.feature.common.Response

interface UserRepo {

    suspend fun registerUser(newUser: NewUser): Response<UserDomain>

    suspend fun getUserDetails() : UserDomain

    suspend fun attemptCacheLogin(): Response<UserDomain>

    suspend fun loginUser(username: String, password: String): Response<UserDomain>

    suspend fun updateUser(updatedInfo: UpdatedUser): Response<UserDomain>

    suspend fun logoutCurrentUser()
}
