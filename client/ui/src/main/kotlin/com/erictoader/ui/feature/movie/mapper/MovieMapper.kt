package com.erictoader.ui.feature.movie.mapper

import com.erictoader.domain.feature.movie.model.MovieDomain
import com.erictoader.ui.feature.movie.model.Movie


fun MovieDomain.mapToUiModel() =
    Movie(
        id = id,
        name = title,
        overview = overview,
        backdropPath = backdropPath,
        posterPath = posterPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
