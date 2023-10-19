package com.erictoader.data.remote.model.details

import com.erictoader.data.remote.model.ModelMapper
import com.erictoader.domain.feature.details.model.SeriesDetailsDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesDetailsData(
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "backdrop_path") var backdropPath: String?,
    @Json(name = "episode_run_time") val episodeRunTime: List<String>?,
    @Json(name = "first_air_date") val firstAirDate: String?,
    @Json(name = "genres") val genres: List<GenreData>?,
    @Json(name = "homepage") val homepage: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "in_production") val inProduction: Boolean?,
    @Json(name = "languages") val languages: List<String>?,
    @Json(name = "last_air_date") val lastAirDate: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "number_of_episodes") val numberOfEpisodes: Int?,
    @Json(name = "number_of_seasons") val numberOfSeasons: Int?,
    @Json(name = "origin_country") val originCountry: List<String>?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "original_name") val originalName: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "seasons") val seasons: List<SeasonsData>?,
    @Json(name = "status") val status: String?,
    @Json(name = "tagline") val tagline: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "vote_average") val voteAverage: Double?,
    @Json(name = "vote_count") val voteCount: Int?,
) : ModelMapper<SeriesDetailsDomain> {

    override fun mapToDomainModel() =
        SeriesDetailsDomain(
            adult = adult,
            backdropPath = backdropPath,
            episodeRunTime = episodeRunTime,
            firstAirDate = firstAirDate,
            genres = genres?.map { it.name },
            homepage = homepage,
            id = id,
            inProduction = inProduction,
            languages = languages,
            lastAirDate = lastAirDate,
            name = name,
            numberOfEpisodes = numberOfEpisodes,
            numberOfSeasons = numberOfSeasons,
            originCountry = originCountry,
            originalLanguage = originalLanguage,
            originalName = originalName,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            seasons = seasons?.map { it.mapToDomainModel() },
            status = status,
            tagline = tagline,
            type = type,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
}
