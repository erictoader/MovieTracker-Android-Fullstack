package com.erictoader.domain.feature.details.usecase.concrete

import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.domain.feature.details.model.AssetModuleDomain
import com.erictoader.domain.feature.details.usecase.abstraction.GetDetailModulesUseCase
import com.erictoader.domain.repo.MovieRepo
import com.erictoader.domain.repo.SeriesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDetailModulesUseCaseImpl(
    private val movieRepo: MovieRepo,
    private val seriesRepo: SeriesRepo
) : GetDetailModulesUseCase {

    override suspend fun invoke(
        assetId: Int,
        assetType: AssetType,
        moduleTypes: List<ModuleType>
    ): List<AssetModuleDomain> =
        withContext(Dispatchers.IO) {
            when (assetType) {
                AssetType.MOVIE_VOD -> movieRepo.getDetailModules(assetId, moduleTypes)
                AssetType.SERIES_VOD -> seriesRepo.getDetailModules(assetId, moduleTypes)
                AssetType.UNKNOWN -> throw IllegalStateException()
            }
        }
}
