package com.erictoader.ui.feature.details.mapper

import com.erictoader.domain.feature.details.model.MovieDetailsDomain
import com.erictoader.ui.feature.details.model.MovieDetails

fun MovieDetailsDomain.mapToUiModel(): MovieDetails =
    MovieDetails(
        genres = genres,
        releaseDate = releaseDate,
        runtime = runtime,
        voteAverage = voteAverage,
    )
