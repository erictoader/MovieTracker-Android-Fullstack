package com.erictoader.ui.feature.common.model

import android.os.Parcelable

abstract class AssetModule (
    open val header: String,
    open val items: List<Asset>
) : Parcelable
