package com.erictoader.movietrackerbackend.service

import com.erictoader.movietrackerbackend.entity.Watchlist
import com.erictoader.movietrackerbackend.repo.WatchlistRepo
import com.erictoader.movietrackerbackend.request.WatchlistRequest
import com.erictoader.movietrackerbackend.response.Response
import com.erictoader.movietrackerbackend.response.ResponseStatus.SUCCESS
import com.erictoader.movietrackerbackend.response.model.WatchlistModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WatchlistService @Autowired constructor(
    private val watchlistRepo: WatchlistRepo
) {

    fun getAllEntries(): Response<List<WatchlistModel>> {
        val entries = watchlistRepo.findAll().map { it.mapToModel() }
        return Response(
            SUCCESS,
            entries
        )
    }

    fun getEntriesByUserId(userId: Int): Response<List<WatchlistModel>> {
        val entries = watchlistRepo.findAllByUserId(userId).map { it.mapToModel() }
        return Response(
            SUCCESS,
            entries
        )
    }

    fun searchForEntry(
        userId: Int,
        assetId: Int,
        assetType: String
    ): Response<WatchlistModel> {
        val entries =
            watchlistRepo.findAllByUserId(userId)
                .filter { it.assetId == assetId }
                .filter { it.assetType == assetType }

        return if (entries.isEmpty()) Response(SUCCESS)
        else Response(SUCCESS, entries.first().mapToModel())
    }

    fun addEntry(request: WatchlistRequest.AddEntry): Response<WatchlistModel> {
        val watchlist = Watchlist(
            userId = request.userId,
            assetId = request.assetId,
            assetType = request.assetType
        )
        val savedWatchlist = watchlistRepo.save(watchlist)
        return Response(SUCCESS, savedWatchlist.mapToModel())
    }

    fun deleteEntryById(request: WatchlistRequest.RemoveEntryById) {
        watchlistRepo.deleteById(request.entryId)
    }
}