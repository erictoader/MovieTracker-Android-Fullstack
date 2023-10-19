package com.erictoader.ui.feature.details.mapper

import com.erictoader.domain.feature.common.VodModuleDomain
import com.erictoader.domain.feature.movie.model.MovieModuleDomain
import com.erictoader.domain.feature.series.model.SeriesModuleDomain
import com.erictoader.ui.feature.movie.mapper.mapToUiModel
import com.erictoader.ui.feature.series.mapper.mapToUiModel

fun VodModuleDomain.mapToUiModel() =
    when (this) {
        is MovieModuleDomain -> this.mapToUiModel()
        is SeriesModuleDomain -> this.mapToUiModel()
        else -> throw IllegalStateException()
    }
