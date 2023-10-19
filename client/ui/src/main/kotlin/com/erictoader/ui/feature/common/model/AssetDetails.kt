package com.erictoader.ui.feature.common.model

import android.os.Parcelable

abstract class AssetDetails(
    open val genres: List<String?>? = null,
    open val releaseDate: String? = null,
    open val voteAverage: Double? = null,
): Parcelable
