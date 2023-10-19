package com.erictoader.ui.feature.common.model

import com.erictoader.domain.feature.common.AssetType
import com.erictoader.ui.feature.common.navigator.NavigationArgument
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
open class VodAsset(
    open val id: Int? = null,
    open val name: String? = null,
    open val overview: String? = null,
    open val backdropPath: String? = null,
    open val posterPath: String? = null,
    open val genreIds: List<Int>? = null,
    open val originalLanguage: String? = null,
    open val popularity: Double? = null,
    open val voteAverage: Double? = null,
    open val voteCount: Int? = null,
    open val assetType: AssetType = AssetType.UNKNOWN
) : Asset, NavigationArgument
