package com.erictoader.ui.feature.series.viewmodel

import com.erictoader.ui.feature.common.viewmodel.Event

sealed class SeriesEvent : Event {

    object LoadModules: SeriesEvent()
    object LoadCarousel: SeriesEvent()
}
