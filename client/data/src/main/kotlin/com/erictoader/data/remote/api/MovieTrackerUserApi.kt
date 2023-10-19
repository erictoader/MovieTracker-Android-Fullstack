package com.erictoader.data.remote.api

import com.erictoader.domain.feature.common.Response
import com.erictoader.data.remote.model.user.UserData
import com.erictoader.data.remote.request.UserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MovieTrackerUserApi {

    @GET("login")
    suspend fun loginUser(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<UserData>

    @POST("register")
    suspend fun registerUser(@Body request: UserRequest.Register): Response<UserData>

    @PUT("update")
    suspend fun updateUser(@Body request: UserRequest.Update): Response<UserData>
}
