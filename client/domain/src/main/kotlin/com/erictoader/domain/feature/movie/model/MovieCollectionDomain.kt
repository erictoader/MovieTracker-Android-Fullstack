package com.erictoader.domain.feature.movie.model

import com.erictoader.domain.feature.common.DomainModel

data class MovieCollectionDomain(
    val items: List<MovieDomain>
) : DomainModel
