package com.erictoader.domain.feature.details.model

import com.erictoader.domain.feature.movie.model.MovieDomain

data class MovieDetailsDomain(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val genres: List<String?>? = null,
    val id: Int? = null,
    val imdbId: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
) : AssetDetailsDomain {

    fun mapToMovieBasic() =
        MovieDomain(
            adult = adult,
            backdropPath = backdropPath,
            genreIds = null,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = null,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
}
