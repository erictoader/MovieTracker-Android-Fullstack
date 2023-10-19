package com.erictoader.data.repo

import com.erictoader.data.cache.abstraction.UserCacheProxy
import com.erictoader.data.remote.abstraction.UserRemoteProxy
import com.erictoader.data.remote.model.user.UserData
import com.erictoader.data.remote.request.UserRequest
import com.erictoader.domain.feature.auth.exception.LoginException
import com.erictoader.domain.feature.auth.model.NewUser
import com.erictoader.domain.feature.auth.model.UpdatedUser
import com.erictoader.domain.feature.auth.model.UserDomain
import com.erictoader.domain.feature.common.Response
import com.erictoader.domain.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepoImpl(
    private val userRemoteProxy: UserRemoteProxy,
    private val userCacheProxy: UserCacheProxy
) : UserRepo {

    override suspend fun registerUser(newUser: NewUser): Response<UserDomain> =
        withContext(Dispatchers.IO) {
            val response = userRemoteProxy.registerUser(
                UserRequest.Register(
                    email = newUser.email,
                    username = newUser.username,
                    password = newUser.password
                )
            )

            response.cacheUserIfSuccessful()

            Response(
                code = response.code,
                message = response.message,
                data = response.data?.mapToDomainModel()
            )
        }

    override suspend fun getUserDetails(): UserDomain =
        withContext(Dispatchers.IO) {
            userCacheProxy.retrieveCachedUser() ?: throw IllegalStateException()
        }

    override suspend fun attemptCacheLogin(): Response<UserDomain> =
        withContext(Dispatchers.IO) {
            val cachedUser = userCacheProxy.retrieveCachedUser()
                ?: throw LoginException("No user session cached")
            val response = loginUser(cachedUser.username, cachedUser.password)

            if (!response.isSuccess) throw LoginException("Invalid login attempt on cached session")
            else response
        }

    override suspend fun loginUser(username: String, password: String): Response<UserDomain> =
        withContext(Dispatchers.IO) {
            val response = userRemoteProxy.loginUser(username, password)

            response.cacheUserIfSuccessful()

            Response(
                code = response.code,
                message = response.message,
                data = response.data?.mapToDomainModel()
            )
        }

    override suspend fun updateUser(updatedInfo: UpdatedUser): Response<UserDomain> =
        withContext(Dispatchers.IO) {
            val loggedInUserId =
                userCacheProxy.retrieveCachedUser()?.id ?: throw IllegalStateException()

            val response = userRemoteProxy.updateUser(
                UserRequest.Update(
                    id = loggedInUserId,
                    firstName = updatedInfo.firstName,
                    lastName = updatedInfo.lastName,
                    email = updatedInfo.email,
                    password = updatedInfo.password,
                )
            )

            response.cacheUserIfSuccessful()

            Response(
                code = response.code,
                message = response.message,
                data = response.data?.mapToDomainModel()
            )
        }

    override suspend fun logoutCurrentUser() =
        withContext(Dispatchers.IO) {
            userCacheProxy.clearCachedUser()
        }

    private suspend fun Response<UserData>.cacheUserIfSuccessful() {
        data?.let { userData ->
            if (isSuccess) userCacheProxy.cacheUser(userData.mapToDomainModel())
        }
    }
}
