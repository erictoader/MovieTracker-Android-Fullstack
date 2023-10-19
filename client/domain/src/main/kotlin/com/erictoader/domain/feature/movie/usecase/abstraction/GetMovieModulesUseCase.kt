package com.erictoader.domain.feature.movie.usecase.abstraction

import com.erictoader.domain.feature.movie.model.MovieModuleDomain

interface GetMovieModulesUseCase {

    suspend operator fun invoke(): List<MovieModuleDomain>
}
