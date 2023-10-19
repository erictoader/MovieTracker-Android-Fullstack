package com.erictoader.data.remote.abstraction


import com.erictoader.data.remote.model.user.UserData
import com.erictoader.data.remote.request.UserRequest
import com.erictoader.domain.feature.common.Response

interface UserRemoteProxy {

    suspend fun registerUser(request: UserRequest.Register): Response<UserData>

    suspend fun loginUser(username: String, password: String): Response<UserData>

    suspend fun updateUser(request: UserRequest.Update): Response<UserData>

}
