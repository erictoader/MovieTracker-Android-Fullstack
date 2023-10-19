package com.erictoader.ui.feature.details.mapper

import com.erictoader.domain.feature.details.model.ReviewDomain
import com.erictoader.domain.feature.details.model.ReviewModuleDomain
import com.erictoader.ui.feature.details.model.Review
import com.erictoader.ui.feature.details.model.ReviewModule

fun ReviewModuleDomain.mapToUiModel() =
    ReviewModule(
        header = header,
        items = items.map { it.mapToUiModel() }
    )

fun ReviewDomain.mapToUiModel() =
    Review(
        author = author,
        content = content
    )
