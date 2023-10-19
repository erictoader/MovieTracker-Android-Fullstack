package com.erictoader.ui.feature.details.mapper

import com.erictoader.domain.feature.common.VodModuleDomain
import com.erictoader.domain.feature.details.model.CastModuleDomain
import com.erictoader.domain.feature.details.model.AssetModuleDomain
import com.erictoader.domain.feature.details.model.ReviewModuleDomain


fun AssetModuleDomain.mapToUiModel() =
    when (this) {
        is CastModuleDomain -> this.mapToUiModel()
        is ReviewModuleDomain -> this.mapToUiModel()
        is VodModuleDomain -> this.mapToUiModel()
        else -> throw IllegalStateException()
    }
