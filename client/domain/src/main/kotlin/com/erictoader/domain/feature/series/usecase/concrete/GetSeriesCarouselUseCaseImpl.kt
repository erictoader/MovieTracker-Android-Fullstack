package com.erictoader.domain.feature.series.usecase.concrete

import com.erictoader.domain.feature.series.model.SeriesCollectionDomain
import com.erictoader.domain.feature.series.usecase.abstraction.GetSeriesCarouselUseCase
import com.erictoader.domain.repo.SeriesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSeriesCarouselUseCaseImpl(
    private val seriesRepo: SeriesRepo
) : GetSeriesCarouselUseCase {

    override suspend fun invoke(): SeriesCollectionDomain =
        withContext(Dispatchers.IO) {
            seriesRepo.getSeriesCarousel()
        }
}
