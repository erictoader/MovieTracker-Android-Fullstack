package com.erictoader.ui.feature.movie.model.ghost

import com.erictoader.ui.feature.common.model.ghost.GhostAsset
import com.erictoader.ui.feature.movie.model.MovieCollection

object GhostMovieCollection : MovieCollection(List(1) { GhostMovie }), GhostAsset
