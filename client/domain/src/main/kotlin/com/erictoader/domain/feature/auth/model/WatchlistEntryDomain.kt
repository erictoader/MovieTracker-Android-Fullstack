package com.erictoader.domain.feature.auth.model

import com.erictoader.domain.feature.common.DomainModel

open class WatchlistEntryDomain(
    val id: Int = 0,
    val movieId: Int,
    val assetType: String,
    val createdAt: String = ""
) : DomainModel
