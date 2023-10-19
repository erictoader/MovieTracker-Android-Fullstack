package com.erictoader.ui.feature.details.model

import com.erictoader.ui.feature.common.model.AssetDetails
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesDetails(
    override val genres: List<String?>? = null,
    val firstAirDate: String? = null,
    override val voteAverage: Double? = null,
    val numberOfSeasons: Int? = null,
    val numberOfEpisodes: Int? = null
): AssetDetails(releaseDate = firstAirDate)
