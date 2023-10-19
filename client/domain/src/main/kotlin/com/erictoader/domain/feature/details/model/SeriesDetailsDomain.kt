package com.erictoader.domain.feature.details.model

import com.erictoader.domain.feature.series.model.SeriesDomain

data class SeriesDetailsDomain(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val episodeRunTime: List<String>? = null,
    val firstAirDate: String? = null,
    val genres: List<String?>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val inProduction: Boolean? = null,
    val languages: List<String>? = null,
    val lastAirDate: String? = null,
    val name: String? = null,
    val nextEpisodeToAir: String? = null,
    val numberOfEpisodes: Int? = null,
    val numberOfSeasons: Int? = null,
    val originCountry: List<String>? = null,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val seasons: List<SeasonsDomain>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
) : AssetDetailsDomain {

    fun mapToSeriesBasic() =
        SeriesDomain(
            backdropPath = backdropPath,
            firstAirDate = firstAirDate,
            genreIds = emptyList(),
            id = id,
            name = name,
            originCountry = originCountry,
            originalLanguage = originalLanguage,
            originalName = originalName,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
}
