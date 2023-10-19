package com.erictoader.domain.feature.movie.usecase.abstraction

import com.erictoader.domain.feature.movie.model.MovieCollectionDomain

interface GetMovieCarouselUseCase {

    suspend operator fun invoke(): MovieCollectionDomain
}
