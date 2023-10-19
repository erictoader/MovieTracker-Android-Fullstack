package com.erictoader.data.remote.api

import com.erictoader.domain.feature.common.Response
import com.erictoader.data.remote.model.watchlist.WatchlistEntryData
import com.erictoader.data.remote.request.WatchlistRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieTrackerWatchlistApi {

    @GET("getByUserId")
    suspend fun getEntriesByUserId(
        @Query("userId") userId: Int
    ): Response<List<WatchlistEntryData>>

    @GET("searchForEntry")
    suspend fun searchForEntry(
        @Query("userId") userId: Int,
        @Query("assetId") assetId: Int,
        @Query("assetType") assetType: String
    ): Response<WatchlistEntryData>

    @POST("add")
    suspend fun addEntry(
        @Body request: WatchlistRequest.AddEntry
    ): Response<WatchlistEntryData>

    @POST("deleteEntry")
    suspend fun deleteEntryById(
        @Body request: WatchlistRequest.RemoveEntryById
    )

}
