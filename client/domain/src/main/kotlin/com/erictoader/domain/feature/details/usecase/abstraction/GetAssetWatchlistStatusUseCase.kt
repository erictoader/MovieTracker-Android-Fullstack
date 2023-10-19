package com.erictoader.domain.feature.details.usecase.abstraction

import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.AssetType

interface GetAssetWatchlistStatusUseCase {

    suspend operator fun invoke(assetId: Int, assetType: AssetType): WatchlistEntryDomain?
}
