package com.erictoader.domain.repo

import com.erictoader.domain.feature.auth.model.NewWatchlistEntry
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.AssetType

interface WatchlistRepo {

    suspend fun getMovieEntries(): List<WatchlistEntryDomain>

    suspend fun getSeriesEntries(): List<WatchlistEntryDomain>

    suspend fun searchForAssetEntry(assetId: Int, assetType: AssetType): WatchlistEntryDomain?

    suspend fun addEntry(newEntry: NewWatchlistEntry): WatchlistEntryDomain

    suspend fun deleteEntryById(id: Int)
}
