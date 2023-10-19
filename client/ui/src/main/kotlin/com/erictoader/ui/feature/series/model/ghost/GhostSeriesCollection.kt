package com.erictoader.ui.feature.series.model.ghost

import com.erictoader.ui.feature.common.model.ghost.GhostAsset
import com.erictoader.ui.feature.series.model.SeriesCollection

object GhostSeriesCollection : SeriesCollection(List(1) { GhostSeries }), GhostAsset
