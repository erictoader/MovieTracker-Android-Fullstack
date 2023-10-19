package com.erictoader.data.repo

import com.erictoader.data.remote.abstraction.MovieRemoteProxy
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.domain.feature.details.model.AssetModuleDomain
import com.erictoader.domain.feature.details.model.CastModuleDomain
import com.erictoader.domain.feature.details.model.MovieDetailsDomain
import com.erictoader.domain.feature.details.model.ReviewModuleDomain
import com.erictoader.domain.feature.movie.model.MovieCollectionDomain
import com.erictoader.domain.feature.movie.model.MovieModuleDomain
import com.erictoader.domain.repo.MovieRepo
import com.erictoader.domain.translator.TRANSLATION_LATEST
import com.erictoader.domain.translator.TRANSLATION_MODULE_CAST
import com.erictoader.domain.translator.TRANSLATION_MODULE_REVIEWS
import com.erictoader.domain.translator.TRANSLATION_NOW_PLAYING
import com.erictoader.domain.translator.TRANSLATION_POPULAR
import com.erictoader.domain.translator.TRANSLATION_RECOMMENDED
import com.erictoader.domain.translator.TRANSLATION_TOP_RATED
import com.erictoader.domain.translator.TRANSLATION_UPCOMING
import com.erictoader.domain.translator.TRANSLATION_WATCHLIST
import com.erictoader.domain.translator.Translator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class MovieRepoImpl(
    private val movieRemoteProxy: MovieRemoteProxy,
    private val translator: Translator
) : MovieRepo {

    override suspend fun getMovieWatchlistModule(watchlist: List<WatchlistEntryDomain>): MovieModuleDomain =
        withContext(Dispatchers.IO) {
            MovieModuleDomain(
                header = translator.fetchTranslation(TRANSLATION_WATCHLIST),
                items = watchlist
                    .filter { it.assetType == AssetType.MOVIE_VOD.name }
                    .map { getMovieDetails(it.movieId) }
                    .map { it.mapToMovieBasic() }
            )
        }

    override suspend fun getMovieModules(): List<MovieModuleDomain> =
        withContext(Dispatchers.IO) {
            mapOf(
                Pair(NOW_PLAYING_REQUEST_PATH, TRANSLATION_NOW_PLAYING),
                Pair(POPULAR_REQUEST_PATH, TRANSLATION_POPULAR),
                Pair(UPCOMING_REQUEST_PATH, TRANSLATION_UPCOMING),
                Pair(LATEST_REQUEST_PATH, TRANSLATION_LATEST),
                Pair(TOP_RATED_REQUEST_PATH, TRANSLATION_TOP_RATED)
            ).map { (path, translationKey) ->
                async {
                    MovieModuleDomain(
                        header = translator.fetchTranslation(translationKey),
                        items = movieRemoteProxy.getMovieCategory(path)
                            .map { it.mapToDomainModel() }
                    )
                }
            }.awaitAll()
        }


    override suspend fun getMovieCarousel(): MovieCollectionDomain =
        withContext(Dispatchers.IO) {
            MovieCollectionDomain(
                movieRemoteProxy
                    .getMovieCategory(POPULAR_REQUEST_PATH)
                    .map { it.mapToDomainModel() }
                    .filter { it.backdropPath != null }
                    .take(5)
            )
        }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDomain =
        withContext(Dispatchers.IO) {
            movieRemoteProxy.getMovieDetails(movieId).mapToDomainModel()
        }

    override suspend fun getDetailModules(
        movieId: Int,
        moduleTypes: List<ModuleType>
    ): List<AssetModuleDomain> =
        withContext(Dispatchers.IO) {
            moduleTypes.map { moduleType ->
                async {
                    when (moduleType) {
                        ModuleType.CAST_MODULE ->
                            CastModuleDomain(
                                header = translator.fetchTranslation(TRANSLATION_MODULE_CAST),
                                items = movieRemoteProxy
                                    .getMovieCredits(movieId)
                                    .cast
                                    ?.map { it.mapToDomainModel() }
                                    ?: listOf()
                            )

                        ModuleType.REVIEWS_MODULE ->
                            ReviewModuleDomain(
                                header = translator.fetchTranslation(TRANSLATION_MODULE_REVIEWS),
                                items = movieRemoteProxy
                                    .getMovieReviews(movieId)
                                    .results
                                    ?.map { it.mapToDomainModel() }
                                    ?: listOf()
                            )

                        ModuleType.RECOMMENDED_MODULE ->
                            MovieModuleDomain(
                                header = translator.fetchTranslation(TRANSLATION_RECOMMENDED),
                                items = movieRemoteProxy.getMovieRecommendations(movieId)
                                    .map { it.mapToDomainModel() }
                            )

                        else -> throw IllegalStateException()
                    }
                }
            }.awaitAll()
        }

    companion object {
        const val NOW_PLAYING_REQUEST_PATH = "now_playing"
        const val POPULAR_REQUEST_PATH = "popular"
        const val UPCOMING_REQUEST_PATH = "upcoming"
        const val LATEST_REQUEST_PATH = "latest"
        const val TOP_RATED_REQUEST_PATH = "top_rated"
    }

}
