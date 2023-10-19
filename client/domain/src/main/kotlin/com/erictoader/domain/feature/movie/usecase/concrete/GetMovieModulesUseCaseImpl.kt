package com.erictoader.domain.feature.movie.usecase.concrete

import com.erictoader.domain.feature.movie.model.MovieModuleDomain
import com.erictoader.domain.feature.movie.usecase.abstraction.GetMovieModulesUseCase
import com.erictoader.domain.repo.MovieRepo
import com.erictoader.domain.repo.WatchlistRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetMovieModulesUseCaseImpl(
    private val movieRepo: MovieRepo,
    private val watchlistRepo: WatchlistRepo
) : GetMovieModulesUseCase {

    override suspend fun invoke(): List<MovieModuleDomain> =
        withContext(Dispatchers.IO) {
            val deferredWatchlistEntries =
                async { movieRepo.getMovieWatchlistModule(watchlistRepo.getMovieEntries()) }

            val deferredModules = async { movieRepo.getMovieModules() }

            listOf(deferredWatchlistEntries.await()) + deferredModules.await()
        }
}
