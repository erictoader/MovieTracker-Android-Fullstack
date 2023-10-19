package com.erictoader.data.remote.model.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesData(
    @Json(name = "page") val page: Int? = null,
    @Json(name = "results") val results: List<MovieBasicData>? = null,
    @Json(name = "total_pages") val totalPages: Int? = null,
    @Json(name = "total_results") val totalResults: Int? = null
)
