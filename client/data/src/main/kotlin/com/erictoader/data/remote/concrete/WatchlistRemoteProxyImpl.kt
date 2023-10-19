package com.erictoader.data.remote.concrete

import com.erictoader.data.remote.abstraction.WatchlistRemoteProxy
import com.erictoader.data.remote.api.MovieTrackerWatchlistApi
import com.erictoader.data.remote.model.watchlist.WatchlistEntryData
import com.erictoader.data.remote.request.WatchlistRequest

class WatchlistRemoteProxyImpl(
    private val api: MovieTrackerWatchlistApi
) : WatchlistRemoteProxy {

    override suspend fun getEntriesByUserId(userId: Int): List<WatchlistEntryData> =
        api.getEntriesByUserId(userId).data ?: listOf()

    override suspend fun searchForEntry(
        userId: Int,
        assetId: Int,
        assetType: String
    ): WatchlistEntryData? =
        api.searchForEntry(userId, assetId, assetType).data

    override suspend fun addEntry(request: WatchlistRequest.AddEntry): WatchlistEntryData =
        api.addEntry(request).data ?: TODO("Add custom exception")

    override suspend fun deleteEntryById(request: WatchlistRequest.RemoveEntryById) =
        api.deleteEntryById(request)
}
