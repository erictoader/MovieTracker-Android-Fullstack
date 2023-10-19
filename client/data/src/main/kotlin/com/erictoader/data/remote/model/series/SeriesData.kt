package com.erictoader.data.remote.model.series

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesData(
    @Json(name = "page") val page: Int? = null,
    @Json(name = "results") val results: List<SeriesBasicData>? = null,
    @Json(name = "total_pages") val totalPages: Int? = null,
    @Json(name = "total_results") val totalResults: Int? = null
)
