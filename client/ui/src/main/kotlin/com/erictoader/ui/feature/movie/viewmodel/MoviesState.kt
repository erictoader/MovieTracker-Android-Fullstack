package com.erictoader.ui.feature.movie.viewmodel

import com.erictoader.ui.feature.common.viewmodel.State
import com.erictoader.ui.feature.movie.model.MovieCollection
import com.erictoader.ui.feature.movie.model.MovieModule

sealed class MoviesState : State {

    object Init : MoviesState()
    data class ModulesLoaded(val modules: List<MovieModule>) : MoviesState()
    data class CarouselLoaded(val collection: MovieCollection) : MoviesState()
    object Error : MoviesState()
}
