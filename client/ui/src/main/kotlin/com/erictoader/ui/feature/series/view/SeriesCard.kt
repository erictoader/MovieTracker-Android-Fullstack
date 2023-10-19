package com.erictoader.ui.feature.series.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.feature.common.view.asset.AssetCard
import com.erictoader.ui.feature.series.model.Series

@Composable
fun SeriesCard(
    navController: NavController = rememberNavController(),
    series: Series
) = AssetCard(asset = series, navController = navController)

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_SeriesCard() {
    SeriesCard(
        series = Series(
            posterPath = null,
            backdropPath = null
        )
    )
}
