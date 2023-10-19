package com.erictoader.domain.feature.details.model

data class CastModuleDomain(
    val header: String,
    val items: List<CastDomain>
): AssetModuleDomain
