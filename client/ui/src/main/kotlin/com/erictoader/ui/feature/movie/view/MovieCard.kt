package com.erictoader.ui.feature.movie.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.feature.common.view.asset.AssetCard
import com.erictoader.ui.feature.movie.model.Movie

@Composable
fun MovieCard(
    navController: NavController = rememberNavController(),
    movie: Movie
) = AssetCard(asset = movie, navController = navController)

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_MovieCard() {
    MovieCard(
        movie = Movie(
            posterPath = null,
            backdropPath = null
        )
    )
}
