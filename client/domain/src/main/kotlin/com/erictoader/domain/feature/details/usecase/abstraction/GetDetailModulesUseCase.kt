package com.erictoader.domain.feature.details.usecase.abstraction

import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.domain.feature.details.model.AssetModuleDomain

interface GetDetailModulesUseCase {

    suspend operator fun invoke(
        assetId: Int,
        assetType: AssetType,
        moduleTypes: List<ModuleType>
    ): List<AssetModuleDomain>
}
