package com.erictoader.ui.feature.details.model

import com.erictoader.ui.feature.common.model.Asset
import kotlinx.parcelize.Parcelize

@Parcelize
open class Review(
    val author: String? = null,
    val content: String? = null
) : Asset
