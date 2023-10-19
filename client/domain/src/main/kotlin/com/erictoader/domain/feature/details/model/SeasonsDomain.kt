package com.erictoader.domain.feature.details.model

import com.erictoader.domain.feature.common.DomainModel

data class SeasonsDomain(
    val airDate: String? = null,
    val episodeCount: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val seasonNumber: Int? = null
) : DomainModel
