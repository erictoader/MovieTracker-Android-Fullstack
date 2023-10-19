package com.erictoader.ui.feature.series.model.ghost

import com.erictoader.ui.feature.common.model.ghost.GhostAsset
import com.erictoader.ui.feature.series.model.SeriesModule

object GhostSeriesModule : SeriesModule("", List(10) { GhostSeries }), GhostAsset
