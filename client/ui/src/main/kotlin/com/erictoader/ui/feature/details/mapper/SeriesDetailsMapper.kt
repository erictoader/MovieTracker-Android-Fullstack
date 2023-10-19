package com.erictoader.ui.feature.details.mapper

import com.erictoader.domain.feature.details.model.SeriesDetailsDomain
import com.erictoader.ui.feature.details.model.SeriesDetails

fun SeriesDetailsDomain.mapToUiModel(): SeriesDetails =
    SeriesDetails(
        genres = genres,
        firstAirDate = firstAirDate,
        voteAverage = voteAverage,
        numberOfSeasons = numberOfSeasons,
        numberOfEpisodes = numberOfEpisodes
    )
