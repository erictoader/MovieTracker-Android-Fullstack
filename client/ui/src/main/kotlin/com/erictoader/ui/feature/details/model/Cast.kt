package com.erictoader.ui.feature.details.model

import com.erictoader.ui.feature.common.model.Asset
import kotlinx.parcelize.Parcelize

@Parcelize
open class Cast(
    val id: Int? = null,
    val name: String? = null,
    val profilePath: String? = null,
    val character: String? = null,
) : Asset
