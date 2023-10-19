package com.erictoader.domain.feature.details.model

import com.erictoader.domain.feature.common.DomainModel

data class CastDomain(
    val adult: Boolean? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val knownForDepartment: String? = null,
    val name: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val profilePath: String? = null,
    val castId: Int? = null,
    val character: String? = null,
    val creditId: String? = null,
    val order: Int? = null
): DomainModel
