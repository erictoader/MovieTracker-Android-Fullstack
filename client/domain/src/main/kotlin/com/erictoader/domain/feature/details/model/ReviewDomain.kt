package com.erictoader.domain.feature.details.model

import com.erictoader.domain.feature.common.DomainModel

data class ReviewDomain(
    val author: String?,
    val content: String?,
    val createdAt: String?,
    val id: String?,
    val updatedAt: String?,
    val url: String?
) : DomainModel
