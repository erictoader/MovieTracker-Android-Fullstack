package com.erictoader.domain.feature.details.usecase.concrete

import com.erictoader.domain.feature.details.usecase.abstraction.RemoveAssetFromWatchlistUseCase
import com.erictoader.domain.repo.WatchlistRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoveAssetFromWatchlistUseCaseImpl(
    private val watchlistRepo: WatchlistRepo
) : RemoveAssetFromWatchlistUseCase {

    override suspend fun invoke(entryId: Int) =
        withContext(Dispatchers.IO) {
            watchlistRepo.deleteEntryById(entryId)
        }
}
