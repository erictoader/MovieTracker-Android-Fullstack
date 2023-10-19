package com.erictoader.ui.feature.movie.model

import android.os.Parcelable
import com.erictoader.ui.feature.common.model.AssetCollection
import kotlinx.parcelize.Parcelize

@Parcelize
open class MovieCollection(
    override val items: List<Movie>
) : AssetCollection(items), Parcelable
