package com.erictoader.data.remote.model.details

import com.erictoader.data.remote.model.ModelMapper
import com.erictoader.domain.feature.details.model.SeasonsDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonsData(
    @Json(name = "air_date") val airDate: String?,
    @Json(name = "episode_count") val episodeCount: Int?,
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "season_number") val seasonNumber: Int?
) : ModelMapper<SeasonsDomain> {

    override fun mapToDomainModel() =
        SeasonsDomain(
            airDate = airDate,
            episodeCount = episodeCount,
            id = id,
            name = name,
            overview = overview,
            posterPath = posterPath,
            seasonNumber = seasonNumber,
        )
}
