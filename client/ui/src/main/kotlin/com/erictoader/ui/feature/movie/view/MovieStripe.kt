package com.erictoader.ui.feature.movie.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.feature.common.model.AssetModule
import com.erictoader.ui.feature.common.view.asset.AssetStripe
import com.erictoader.ui.feature.movie.model.MovieModule

@Composable
fun MovieStripe(
    navController: NavController = rememberNavController(),
    movieModule: AssetModule
) = AssetStripe(assetModule = movieModule, navController = navController)

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_MovieStripe() {
    MovieStripe(
        movieModule = MovieModule(
            header = "Latest Releases",
            items = listOf()
        )
    )
}
