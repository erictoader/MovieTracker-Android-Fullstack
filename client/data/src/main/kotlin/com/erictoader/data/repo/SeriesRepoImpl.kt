package com.erictoader.data.repo

import com.erictoader.data.remote.abstraction.SeriesRemoteProxy
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.domain.feature.details.model.AssetModuleDomain
import com.erictoader.domain.feature.details.model.CastModuleDomain
import com.erictoader.domain.feature.details.model.ReviewModuleDomain
import com.erictoader.domain.feature.details.model.SeriesDetailsDomain
import com.erictoader.domain.feature.series.model.SeriesCollectionDomain
import com.erictoader.domain.feature.series.model.SeriesModuleDomain
import com.erictoader.domain.repo.SeriesRepo
import com.erictoader.domain.translator.TRANSLATION_AIRING_TODAY
import com.erictoader.domain.translator.TRANSLATION_LATEST
import com.erictoader.domain.translator.TRANSLATION_MODULE_CAST
import com.erictoader.domain.translator.TRANSLATION_MODULE_REVIEWS
import com.erictoader.domain.translator.TRANSLATION_ON_THE_AIR
import com.erictoader.domain.translator.TRANSLATION_POPULAR
import com.erictoader.domain.translator.TRANSLATION_RECOMMENDED
import com.erictoader.domain.translator.TRANSLATION_TOP_RATED
import com.erictoader.domain.translator.TRANSLATION_WATCHLIST
import com.erictoader.domain.translator.Translator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class SeriesRepoImpl(
    private val seriesRemoteProxy: SeriesRemoteProxy,
    private val translator: Translator
) : SeriesRepo {

    override suspend fun getSeriesModules(): List<SeriesModuleDomain> =
        withContext(Dispatchers.IO) {
            mapOf(
                Pair(ON_THE_AIR_REQUEST_PATH, TRANSLATION_ON_THE_AIR),
                Pair(POPULAR_REQUEST_PATH, TRANSLATION_POPULAR),
                Pair(AIRING_TODAY_REQUEST_PATH, TRANSLATION_AIRING_TODAY),
                Pair(LATEST_REQUEST_PATH, TRANSLATION_LATEST),
                Pair(TOP_RATED_REQUEST_PATH, TRANSLATION_TOP_RATED)
            ).map { (path, translationKey) ->
                async {
                    SeriesModuleDomain(
                        header = translator.fetchTranslation(translationKey),
                        items = seriesRemoteProxy.getSeriesCategory(path)
                            .map { it.mapToDomainModel() }
                            .shuffled()
                    )
                }
            }.awaitAll()
        }

    override suspend fun getSeriesWatchlistModule(watchlist: List<WatchlistEntryDomain>): SeriesModuleDomain =
        withContext(Dispatchers.IO) {
            SeriesModuleDomain(
                header = translator.fetchTranslation(TRANSLATION_WATCHLIST),
                items = watchlist
                    .filter { it.assetType == AssetType.MOVIE_VOD.name }
                    .map { getSeriesDetails(it.movieId) }
                    .map { it.mapToSeriesBasic() }
            )
        }

    override suspend fun getSeriesCarousel(): SeriesCollectionDomain =
        withContext(Dispatchers.IO) {
            SeriesCollectionDomain(
                seriesRemoteProxy
                    .getSeriesCategory(POPULAR_REQUEST_PATH)
                    .map { it.mapToDomainModel() }
                    .filter { it.backdropPath != null }
                    .take(5)
            )
        }

    override suspend fun getDetailModules(
        seriesId: Int,
        moduleTypes: List<ModuleType>
    ): List<AssetModuleDomain> =
        withContext(Dispatchers.IO) {
            moduleTypes.map { moduleType ->
                async {
                    when (moduleType) {
                        ModuleType.CAST_MODULE ->
                            CastModuleDomain(
                                header = translator.fetchTranslation(TRANSLATION_MODULE_CAST),
                                items = seriesRemoteProxy
                                    .getSeriesCredits(seriesId)
                                    .cast
                                    ?.map { it.mapToDomainModel() }
                                    ?: listOf()
                            )

                        ModuleType.REVIEWS_MODULE ->
                            ReviewModuleDomain(
                                header = translator.fetchTranslation(TRANSLATION_MODULE_REVIEWS),
                                items = seriesRemoteProxy
                                    .getSeriesReviews(seriesId)
                                    .results
                                    ?.map { it.mapToDomainModel() }
                                    ?: listOf()
                            )

                        ModuleType.RECOMMENDED_MODULE ->
                            SeriesModuleDomain(
                                header = translator.fetchTranslation(TRANSLATION_RECOMMENDED),
                                items = seriesRemoteProxy.getSeriesRecommendations(seriesId)
                                    .map { it.mapToDomainModel() }
                            )

                        else -> throw IllegalStateException()
                    }
                }
            }.awaitAll()
        }

    override suspend fun getSeriesDetails(seriesId: Int): SeriesDetailsDomain =
        withContext(Dispatchers.IO) {
            seriesRemoteProxy.getSeriesDetails(seriesId).mapToDomainModel()
        }

    companion object {
        const val ON_THE_AIR_REQUEST_PATH = "on_the_air"
        const val POPULAR_REQUEST_PATH = "popular"
        const val AIRING_TODAY_REQUEST_PATH = "airing_today"
        const val LATEST_REQUEST_PATH = "latest"
        const val TOP_RATED_REQUEST_PATH = "top_rated"
    }

}
