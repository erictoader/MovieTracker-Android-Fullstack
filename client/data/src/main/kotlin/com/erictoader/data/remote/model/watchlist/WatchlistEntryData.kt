package com.erictoader.data.remote.model.watchlist

import com.erictoader.data.remote.model.ModelMapper
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WatchlistEntryData(
    @Json(name = "id") val id: Int,
    @Json(name = "userId") val userId: Int,
    @Json(name = "assetId") val movieId: Int,
    @Json(name = "assetType") val assetType: String,
    @Json(name = "createdAt") val createdAt: String
) : ModelMapper<WatchlistEntryDomain> {

    override fun mapToDomainModel() =
        WatchlistEntryDomain(
            id = id,
            movieId = movieId,
            assetType = assetType,
            createdAt = createdAt,
        )
}
