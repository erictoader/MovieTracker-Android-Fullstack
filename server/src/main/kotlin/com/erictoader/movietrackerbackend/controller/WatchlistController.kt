package com.erictoader.movietrackerbackend.controller

import com.erictoader.movietrackerbackend.request.WatchlistRequest
import com.erictoader.movietrackerbackend.response.Response
import com.erictoader.movietrackerbackend.response.model.WatchlistModel
import com.erictoader.movietrackerbackend.service.WatchlistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin("*")
@RequestMapping("watchlist")
@RestController
class WatchlistController @Autowired constructor(
    private val watchlistService: WatchlistService
) {
    @GetMapping("/get")
    fun getAll(): Response<List<WatchlistModel>> =
        watchlistService.getAllEntries()

    @GetMapping("/getByUserId")
    fun getEntriesByUserId(@RequestParam("userId") userId: Int): Response<List<WatchlistModel>> =
        watchlistService.getEntriesByUserId(userId)

    @GetMapping("/searchForEntry")
    fun searchForEntry(
        @RequestParam("userId") userId: Int,
        @RequestParam("assetId") assetId: Int,
        @RequestParam("assetType") assetType: String
    ): Response<WatchlistModel> =
        watchlistService.searchForEntry(
            userId = userId,
            assetId = assetId,
            assetType = assetType
        )

    @PostMapping("/add")
    fun addEntry(@RequestBody request: WatchlistRequest.AddEntry): Response<WatchlistModel> =
        watchlistService.addEntry(request)

    @PostMapping("/deleteEntry")
    fun deleteEntryById(@RequestBody request: WatchlistRequest.RemoveEntryById): Unit =
        watchlistService.deleteEntryById(request)
}
