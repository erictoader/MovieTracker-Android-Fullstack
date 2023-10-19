package com.erictoader.ui.feature.series.mapper

import com.erictoader.domain.feature.series.model.SeriesDomain
import com.erictoader.ui.feature.series.model.Series


fun SeriesDomain.mapToUiModel() =
    Series(
        id = id,
        name = name,
        overview = overview,
        backdropPath = backdropPath,
        posterPath = posterPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
