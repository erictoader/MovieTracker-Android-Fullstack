package com.erictoader.ui.feature.movie.mapper

import com.erictoader.domain.feature.movie.model.MovieCollectionDomain
import com.erictoader.ui.feature.movie.model.MovieCollection

fun MovieCollectionDomain.mapToUiModel() =
    MovieCollection(
        items = items.map { it.mapToUiModel() }
    )
