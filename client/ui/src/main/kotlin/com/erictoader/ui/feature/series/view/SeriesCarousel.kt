package com.erictoader.ui.feature.series.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.feature.common.view.asset.AssetCarousel
import com.erictoader.ui.feature.series.model.SeriesCollection

@Composable
fun SeriesCarousel(
    navController: NavController = rememberNavController(),
    seriesCollection: SeriesCollection
) = AssetCarousel(assetCollection = seriesCollection, navController = navController)

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_SeriesCarousel() {
    SeriesCarousel(
        seriesCollection = SeriesCollection(
            listOf()
        )
    )
}
