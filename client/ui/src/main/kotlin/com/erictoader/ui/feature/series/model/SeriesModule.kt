package com.erictoader.ui.feature.series.model

import android.os.Parcelable
import com.erictoader.ui.feature.common.model.AssetModule
import kotlinx.parcelize.Parcelize

@Parcelize
open class SeriesModule(
    override val header: String,
    override val items: List<Series>
) : AssetModule(header, items), Parcelable
