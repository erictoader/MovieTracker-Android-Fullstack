package com.erictoader.data.remote.model.user

import com.erictoader.data.remote.model.ModelMapper
import com.erictoader.domain.feature.auth.model.UserDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "id") val id: Int,
    @Json(name = "email") val email: String,
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String
) : ModelMapper<UserDomain> {

    override fun mapToDomainModel() =
        UserDomain(
            id = id,
            email = email,
            username = username,
            password = password,
            firstName = firstName,
            lastName = lastName,
        )
}
