package com.erictoader.domain.feature.series.usecase.abstraction

import com.erictoader.domain.feature.series.model.SeriesCollectionDomain

interface GetSeriesCarouselUseCase {

    suspend operator fun invoke(): SeriesCollectionDomain
}
