package com.erictoader.domain.feature.details.usecase.concrete

import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.details.usecase.abstraction.GetAssetWatchlistStatusUseCase
import com.erictoader.domain.repo.WatchlistRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAssetWatchlistStatusUseCaseImpl(
    private val watchlistRepo: WatchlistRepo
) : GetAssetWatchlistStatusUseCase {

    override suspend fun invoke(assetId: Int, assetType: AssetType): WatchlistEntryDomain? =
         withContext(Dispatchers.IO) {
             watchlistRepo.searchForAssetEntry(assetId, assetType)
         }
}
