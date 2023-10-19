package com.erictoader.ui.feature.series.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.feature.common.view.asset.AssetStripe
import com.erictoader.ui.feature.series.model.SeriesModule

@Composable
fun SeriesStripe(
    navController: NavController = rememberNavController(),
    seriesModule: SeriesModule
) = AssetStripe(assetModule = seriesModule, navController = navController)

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_SeriesStripe() {
    SeriesStripe(
        seriesModule = SeriesModule(
            header = "Latest Releases",
            items = listOf()
        )
    )
}
