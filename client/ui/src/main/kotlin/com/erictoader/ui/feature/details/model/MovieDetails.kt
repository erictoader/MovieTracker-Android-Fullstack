package com.erictoader.ui.feature.details.model

import com.erictoader.ui.feature.common.model.AssetDetails
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetails(
    override val genres: List<String?>? = null,
    override val releaseDate: String? = null,
    override val voteAverage: Double? = null,
    val runtime: Int? = null,
): AssetDetails()
