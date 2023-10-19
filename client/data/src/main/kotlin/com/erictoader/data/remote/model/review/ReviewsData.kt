package com.erictoader.data.remote.model.review

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewsData(
    @Json(name = "id") val id: Int?,
    @Json(name = "page") val page: Int?,
    @Json(name = "results") val results: List<ReviewData>?,
    @Json(name = "total_pages") val totalPages: Int?,
    @Json(name = "total_results") val totalResults: Int?
)
