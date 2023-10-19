package com.erictoader.data.remote.model.review

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDetailsData(
    @Json(name = "name") val name: String?,
    @Json(name = "username") val username: String?,
    @Json(name = "avatar_path") val avatarPath: String?,
    @Json(name = "rating") val rating: String?
)
