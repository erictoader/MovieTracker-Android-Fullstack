package com.erictoader.domain.repo

import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.domain.feature.details.model.AssetModuleDomain
import com.erictoader.domain.feature.details.model.SeriesDetailsDomain
import com.erictoader.domain.feature.series.model.SeriesCollectionDomain
import com.erictoader.domain.feature.series.model.SeriesModuleDomain

interface SeriesRepo {

    suspend fun getSeriesModules(): List<SeriesModuleDomain>

    suspend fun getSeriesCarousel(): SeriesCollectionDomain

    suspend fun getSeriesDetails(seriesId: Int): SeriesDetailsDomain

    suspend fun getSeriesWatchlistModule(watchlist: List<WatchlistEntryDomain>) : SeriesModuleDomain

    suspend fun getDetailModules(
        seriesId: Int,
        moduleTypes: List<ModuleType>
    ): List<AssetModuleDomain>
}
