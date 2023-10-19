package com.erictoader.domain.feature.movie.model

import com.erictoader.domain.feature.common.VodModuleDomain

data class MovieModuleDomain(
    val header: String,
    val items: List<MovieDomain>
) : VodModuleDomain
