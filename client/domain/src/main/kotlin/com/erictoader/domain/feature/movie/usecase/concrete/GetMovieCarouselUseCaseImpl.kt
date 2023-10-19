package com.erictoader.domain.feature.movie.usecase.concrete

import com.erictoader.domain.feature.movie.model.MovieCollectionDomain
import com.erictoader.domain.feature.movie.usecase.abstraction.GetMovieCarouselUseCase
import com.erictoader.domain.repo.MovieRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieCarouselUseCaseImpl(
    private val movieRepo: MovieRepo
) : GetMovieCarouselUseCase {

    override suspend fun invoke(): MovieCollectionDomain =
        withContext(Dispatchers.IO) {
            movieRepo.getMovieCarousel()
        }
}
