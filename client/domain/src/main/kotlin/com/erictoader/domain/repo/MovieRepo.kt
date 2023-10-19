package com.erictoader.domain.repo

import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.domain.feature.details.model.AssetModuleDomain
import com.erictoader.domain.feature.details.model.MovieDetailsDomain
import com.erictoader.domain.feature.movie.model.MovieCollectionDomain
import com.erictoader.domain.feature.movie.model.MovieModuleDomain

interface MovieRepo {

    suspend fun getMovieModules(): List<MovieModuleDomain>

    suspend fun getMovieCarousel(): MovieCollectionDomain

    suspend fun getMovieDetails(movieId: Int): MovieDetailsDomain

    suspend fun getMovieWatchlistModule(watchlist: List<WatchlistEntryDomain>) : MovieModuleDomain

    suspend fun getDetailModules(
        movieId: Int,
        moduleTypes: List<ModuleType>
    ): List<AssetModuleDomain>
}
