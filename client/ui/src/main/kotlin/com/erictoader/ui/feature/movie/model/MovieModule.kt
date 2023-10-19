package com.erictoader.ui.feature.movie.model

import com.erictoader.ui.feature.common.model.AssetModule
import kotlinx.parcelize.Parcelize

@Parcelize
open class MovieModule(
    override val header: String,
    override val items: List<Movie>
): AssetModule(header, items)
