package com.erictoader.domain.feature.details.usecase.concrete

import com.erictoader.domain.feature.auth.model.NewWatchlistEntry
import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.details.usecase.abstraction.AddAssetToWatchlistUseCase
import com.erictoader.domain.repo.WatchlistRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddAssetToWatchlistUseCaseImpl(
    private val watchlistRepo: WatchlistRepo
) : AddAssetToWatchlistUseCase {

    override suspend fun invoke(
        assetId: Int,
        assetType: AssetType
    ) =
        withContext(Dispatchers.IO) {
            watchlistRepo.addEntry(
                NewWatchlistEntry(
                    assetId = assetId,
                    assetType = assetType.name
                )
            )
        }
}
