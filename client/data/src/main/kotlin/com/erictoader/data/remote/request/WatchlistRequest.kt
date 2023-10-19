package com.erictoader.data.remote.request

sealed class WatchlistRequest {
    data class AddEntry(
        val userId: Int,
        val assetId: Int,
        val assetType: String
    ) : WatchlistRequest()

    data class RemoveEntryById(
        val entryId: Int
    ) : WatchlistRequest()
}
