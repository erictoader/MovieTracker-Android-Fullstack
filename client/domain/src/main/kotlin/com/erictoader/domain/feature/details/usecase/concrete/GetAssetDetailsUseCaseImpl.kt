package com.erictoader.domain.feature.details.usecase.concrete

import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.details.model.AssetDetailsDomain
import com.erictoader.domain.feature.details.usecase.abstraction.GetAssetDetailsUseCase
import com.erictoader.domain.repo.MovieRepo
import com.erictoader.domain.repo.SeriesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAssetDetailsUseCaseImpl(
    private val movieRepo: MovieRepo,
    private val seriesRepo: SeriesRepo
) : GetAssetDetailsUseCase {

    override suspend fun invoke(assetId: Int, assetType: AssetType): AssetDetailsDomain =
        withContext(Dispatchers.IO) {
            when (assetType) {
                AssetType.MOVIE_VOD -> movieRepo.getMovieDetails(assetId)
                AssetType.SERIES_VOD -> seriesRepo.getSeriesDetails(assetId)
                AssetType.UNKNOWN -> throw IllegalStateException()
            }
        }
}
