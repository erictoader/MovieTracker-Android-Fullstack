package com.erictoader.data.remote.abstraction

import com.erictoader.data.remote.model.watchlist.WatchlistEntryData
import com.erictoader.data.remote.request.WatchlistRequest

interface WatchlistRemoteProxy {

    suspend fun getEntriesByUserId(userId: Int): List<WatchlistEntryData>

    suspend fun searchForEntry(userId: Int, assetId: Int, assetType: String): WatchlistEntryData?

    suspend fun addEntry(request: WatchlistRequest.AddEntry): WatchlistEntryData

    suspend fun deleteEntryById(request: WatchlistRequest.RemoveEntryById)
}
