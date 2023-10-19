package com.erictoader.data.remote.concrete

import com.erictoader.domain.feature.common.Response
import com.erictoader.data.remote.model.user.UserData
import com.erictoader.data.remote.abstraction.UserRemoteProxy
import com.erictoader.data.remote.api.MovieTrackerUserApi
import com.erictoader.data.remote.request.UserRequest

class UserRemoteProxyImpl(
    private val api: MovieTrackerUserApi
) : UserRemoteProxy {

    override suspend fun registerUser(request: UserRequest.Register): Response<UserData> =
        api.registerUser(request)

    override suspend fun loginUser(username: String, password: String): Response<UserData> =
        api.loginUser(username, password)

    override suspend fun updateUser(request: UserRequest.Update): Response<UserData> =
        api.updateUser(request)
}
