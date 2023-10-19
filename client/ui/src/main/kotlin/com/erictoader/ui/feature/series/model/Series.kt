package com.erictoader.ui.feature.series.model

import android.os.Parcelable
import com.erictoader.domain.feature.common.AssetType
import com.erictoader.ui.feature.common.model.VodAsset
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
open class Series(
    override val id: Int? = null,
    override val name: String? = null,
    override val overview: String? = null,
    override val backdropPath: String? = null,
    override val posterPath: String? = null,
    override val genreIds: List<Int>? = null,
    override val originalLanguage: String? = null,
    override val popularity: Double? = null,
    override val voteAverage: Double? = null,
    override val voteCount: Int? = null,
    override val assetType: AssetType = AssetType.SERIES_VOD
) : VodAsset(), Parcelable
