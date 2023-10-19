package com.erictoader.ui.feature.movie.mapper

import com.erictoader.domain.feature.movie.model.MovieModuleDomain
import com.erictoader.ui.feature.movie.model.MovieModule

fun MovieModuleDomain.mapToUiModel() =
    MovieModule(
        header = header,
        items = items.map { it.mapToUiModel() }
    )
