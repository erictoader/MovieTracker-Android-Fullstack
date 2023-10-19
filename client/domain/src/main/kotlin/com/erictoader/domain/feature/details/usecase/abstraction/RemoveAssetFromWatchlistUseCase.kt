package com.erictoader.domain.feature.details.usecase.abstraction

interface RemoveAssetFromWatchlistUseCase {

    suspend operator fun invoke(entryId: Int)
}
