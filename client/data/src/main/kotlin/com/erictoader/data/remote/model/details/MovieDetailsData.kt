package com.erictoader.data.remote.model.details

import com.erictoader.data.remote.model.ModelMapper
import com.erictoader.domain.feature.details.model.MovieDetailsDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsData(
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "backdrop_path") var backdropPath: String?,
    @Json(name = "budget") val budget: Int?,
    @Json(name = "genres") val genres: List<GenreData>?,
    @Json(name = "id") val id: Int?,
    @Json(name = "imdb_id") val imdbId: String?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "original_title") val originalTitle: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "runtime") val runtime: Int?,
    @Json(name = "status") val status: String?,
    @Json(name = "tagline") val tagline: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "vote_average") val voteAverage: Double?,
    @Json(name = "vote_count") val voteCount: Int?
) : ModelMapper<MovieDetailsDomain> {

    override fun mapToDomainModel() =
        MovieDetailsDomain(
            adult = adult,
            backdropPath = backdropPath,
            budget = budget,
            genres = genres?.map { it.name },
            id = id,
            imdbId = imdbId,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            status = status,
            tagline = tagline,
            title = title,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
}
