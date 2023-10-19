package com.erictoader.data.remote.concrete

import com.erictoader.data.remote.model.credits.CreditsData
import com.erictoader.data.remote.model.details.SeriesDetailsData
import com.erictoader.data.remote.model.review.ReviewsData
import com.erictoader.data.remote.model.series.SeriesBasicData
import com.erictoader.data.remote.model.series.SeriesData
import com.erictoader.data.remote.abstraction.SeriesRemoteProxy
import com.erictoader.data.remote.api.TMDBSeriesApi

class SeriesRemoteProxyImpl(
    private val tmdbSeriesApi: TMDBSeriesApi
) : SeriesRemoteProxy {

    override suspend fun getSeriesCategory(category: String): List<SeriesBasicData> =
        tmdbSeriesApi.getSeriesCategory(category).also { completeImagePaths(it) }.results
            ?: listOf()

    override suspend fun getSeriesDetails(seriesId: Int): SeriesDetailsData =
        tmdbSeriesApi.getSeriesDetails(seriesId).also {
            it.posterPath = IMAGE_PATH_COMPLETION + it.posterPath
            it.backdropPath = IMAGE_PATH_COMPLETION + it.backdropPath
        }

    override suspend fun getSeriesCredits(seriesId: Int): CreditsData =
        tmdbSeriesApi.getSeriesCredits(seriesId).also {
            it.cast?.forEach { castMember ->
                castMember.profilePath = IMAGE_PATH_COMPLETION + castMember.profilePath
            }
        }

    override suspend fun getSeriesReviews(seriesId: Int): ReviewsData =
        tmdbSeriesApi.getSeriesReviews(seriesId)

    override suspend fun getSeriesRecommendations(seriesId: Int): List<SeriesBasicData> =
        tmdbSeriesApi.getSeriesRecommendations(seriesId).also { completeImagePaths(it) }.results
            ?: listOf()

    private fun completeImagePaths(seriesData: SeriesData) =
        seriesData.results?.forEach { movie -> assignImagePaths(movie) }

    private fun assignImagePaths(series: SeriesBasicData) {
        series.posterPath = IMAGE_PATH_COMPLETION + series.posterPath
        series.backdropPath = IMAGE_PATH_COMPLETION + series.backdropPath
    }

    private companion object {
        private const val IMAGE_PATH_COMPLETION = "https://image.tmdb.org/t/p/w500"
    }

}
