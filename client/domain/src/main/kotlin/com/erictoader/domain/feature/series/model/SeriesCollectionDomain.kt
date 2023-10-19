package com.erictoader.domain.feature.series.model

import com.erictoader.domain.feature.common.DomainModel

data class SeriesCollectionDomain(
    val items: List<SeriesDomain>
) : DomainModel
