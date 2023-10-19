package com.erictoader.ui.feature.series.model

import android.os.Parcelable
import com.erictoader.ui.feature.common.model.AssetCollection
import kotlinx.parcelize.Parcelize

@Parcelize
open class SeriesCollection(
    override val items: List<Series>
) : AssetCollection(items), Parcelable
