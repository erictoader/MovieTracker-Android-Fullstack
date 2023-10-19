package com.erictoader.ui.feature.series.mapper

import com.erictoader.domain.feature.series.model.SeriesModuleDomain
import com.erictoader.ui.feature.series.model.SeriesModule

fun SeriesModuleDomain.mapToUiModel() =
    SeriesModule(
        header = header,
        items = items.map { it.mapToUiModel() }
    )
