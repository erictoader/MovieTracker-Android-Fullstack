package com.erictoader.domain.feature.details.model

data class ReviewModuleDomain(
    val header: String,
    val items: List<ReviewDomain>
) : AssetModuleDomain
