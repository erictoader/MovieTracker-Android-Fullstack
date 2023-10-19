package com.erictoader.ui.feature.series.mapper

import com.erictoader.domain.feature.series.model.SeriesCollectionDomain
import com.erictoader.ui.feature.series.model.SeriesCollection

fun SeriesCollectionDomain.mapToUiModel() =
    SeriesCollection(
        items = items.map { it.mapToUiModel() }
    )
