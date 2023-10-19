package com.erictoader.data.repo

import com.erictoader.data.cache.abstraction.UserCacheProxy
import com.erictoader.data.remote.abstraction.WatchlistRemoteProxy
import com.erictoader.data.remote.request.WatchlistRequest
import com.erictoader.domain.feature.auth.model.NewWatchlistEntry
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.repo.WatchlistRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WatchlistRepoImpl(
    private val watchlistRemoteProxy: WatchlistRemoteProxy,
    private val userCacheProxy: UserCacheProxy
) : WatchlistRepo {

    override suspend fun getMovieEntries(): List<WatchlistEntryDomain> =
        withContext(Dispatchers.IO) {
            watchlistRemoteProxy.getEntriesByUserId(getLoggedInUserId())
                .filter { it.assetType == AssetType.MOVIE_VOD.name }
                .map { it.mapToDomainModel() }
        }

    override suspend fun getSeriesEntries(): List<WatchlistEntryDomain> =
        withContext(Dispatchers.IO) {
            watchlistRemoteProxy.getEntriesByUserId(getLoggedInUserId())
                .filter { it.assetType == AssetType.SERIES_VOD.name }
                .map { it.mapToDomainModel() }
        }

    override suspend fun searchForAssetEntry(
        assetId: Int,
        assetType: AssetType
    ): WatchlistEntryDomain? =
        withContext(Dispatchers.IO) {
            watchlistRemoteProxy.searchForEntry(
                userId = getLoggedInUserId(),
                assetId = assetId,
                assetType = assetType.name
            )
                ?.mapToDomainModel()
        }

    override suspend fun addEntry(newEntry: NewWatchlistEntry): WatchlistEntryDomain =
        withContext(Dispatchers.IO) {
            val request = WatchlistRequest.AddEntry(
                userId = getLoggedInUserId(),
                assetId = newEntry.assetId,
                assetType = newEntry.assetType
            )

            watchlistRemoteProxy.addEntry(request).mapToDomainModel()
        }

    override suspend fun deleteEntryById(id: Int) =
        withContext(Dispatchers.IO) {
            val request = WatchlistRequest.RemoveEntryById(id)
            watchlistRemoteProxy.deleteEntryById(request)
        }

    private suspend fun getLoggedInUserId(): Int =
        userCacheProxy.retrieveCachedUser()?.id ?: throw IllegalStateException()
}
