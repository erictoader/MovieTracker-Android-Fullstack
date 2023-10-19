package com.erictoader.domain.feature.series.usecase.concrete

import com.erictoader.domain.feature.series.model.SeriesModuleDomain
import com.erictoader.domain.feature.series.usecase.abstraction.GetSeriesModulesUseCase
import com.erictoader.domain.repo.SeriesRepo
import com.erictoader.domain.repo.WatchlistRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetSeriesModulesUseCaseImpl(
    private val seriesRepo: SeriesRepo,
    private val watchlistRepo: WatchlistRepo
) : GetSeriesModulesUseCase {

    override suspend fun invoke(): List<SeriesModuleDomain> =
        withContext(Dispatchers.IO) {
            val deferredWatchlistEntries =
                async { seriesRepo.getSeriesWatchlistModule(watchlistRepo.getSeriesEntries()) }

            val deferredModules = async { seriesRepo.getSeriesModules() }

            listOf(deferredWatchlistEntries.await()) + deferredModules.await()
        }
}
