package com.erictoader.ui.feature.common.view.asset

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.erictoader.ui.feature.common.model.Asset
import com.erictoader.ui.feature.common.model.VodAsset
import com.erictoader.ui.feature.details.model.Cast
import com.erictoader.ui.feature.details.model.Review

@Composable
fun AssetCard(
    navController: NavController,
    asset: Asset
) = when (asset) {
    is VodAsset -> VodCard(navController = navController, vodAsset = asset)
    is Cast -> CastCard(navController = navController, cast = asset)
    is Review -> ReviewCard(navController = navController, review = asset)
    else -> throw IllegalStateException()
}
