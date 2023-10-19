package com.erictoader.domain.feature.details.usecase.abstraction

import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.details.model.AssetDetailsDomain

interface GetAssetDetailsUseCase {

    suspend operator fun invoke(assetId: Int, assetType: AssetType): AssetDetailsDomain
}
