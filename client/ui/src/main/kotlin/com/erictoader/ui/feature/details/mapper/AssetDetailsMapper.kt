package com.erictoader.ui.feature.details.mapper

import com.erictoader.domain.feature.details.model.AssetDetailsDomain
import com.erictoader.domain.feature.details.model.MovieDetailsDomain
import com.erictoader.domain.feature.details.model.SeriesDetailsDomain
import com.erictoader.ui.feature.common.model.AssetDetails

fun AssetDetailsDomain.mapToUiModel(): AssetDetails =
    when (this) {
        is MovieDetailsDomain -> this.mapToUiModel()
        is SeriesDetailsDomain -> this.mapToUiModel()
        else -> throw IllegalStateException()
    }
