package com.erictoader.ui.feature.series.viewmodel

import com.erictoader.ui.feature.common.viewmodel.State
import com.erictoader.ui.feature.series.model.SeriesCollection
import com.erictoader.ui.feature.series.model.SeriesModule

sealed class SeriesState : State {

    object Init : SeriesState()
    data class ModulesLoaded(val modules: List<SeriesModule>) : SeriesState()
    data class CarouselLoaded(val collection: SeriesCollection) : SeriesState()
    object Error : SeriesState()
}
