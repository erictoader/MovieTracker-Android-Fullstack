package com.erictoader.domain.feature.series.model

import com.erictoader.domain.feature.common.VodModuleDomain

data class SeriesModuleDomain(
    val header: String,
    val items: List<SeriesDomain>
) : VodModuleDomain
