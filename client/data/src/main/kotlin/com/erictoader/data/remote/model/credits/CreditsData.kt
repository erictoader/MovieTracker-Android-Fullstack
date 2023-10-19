package com.erictoader.data.remote.model.credits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditsData(
    @Json(name = "id") val id: Int?,
    @Json(name = "cast") val cast: List<CastData>?
)
