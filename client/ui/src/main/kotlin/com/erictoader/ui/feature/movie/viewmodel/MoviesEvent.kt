package com.erictoader.ui.feature.movie.viewmodel

import com.erictoader.ui.feature.common.viewmodel.Event

sealed class MoviesEvent : Event {

    object LoadModules: MoviesEvent()
    object LoadCarousel: MoviesEvent()
}
