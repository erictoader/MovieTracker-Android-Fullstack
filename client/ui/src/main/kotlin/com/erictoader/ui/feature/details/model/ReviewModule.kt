package com.erictoader.ui.feature.details.model

import com.erictoader.ui.feature.common.model.AssetModule
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewModule(
    override val header: String,
    override val items: List<Review>
): AssetModule(header, items)
