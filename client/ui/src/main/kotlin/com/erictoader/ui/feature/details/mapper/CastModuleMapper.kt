package com.erictoader.ui.feature.details.mapper

import com.erictoader.domain.feature.details.model.CastDomain
import com.erictoader.domain.feature.details.model.CastModuleDomain
import com.erictoader.ui.feature.details.model.Cast
import com.erictoader.ui.feature.details.model.CastModule

fun CastModuleDomain.mapToUiModel() =
    CastModule(
        header = header,
        items = items.map { it.mapToUiModel() }
    )

fun CastDomain.mapToUiModel() =
    Cast(
        id = id,
        name = name,
        profilePath = profilePath,
        character = character,
    )
