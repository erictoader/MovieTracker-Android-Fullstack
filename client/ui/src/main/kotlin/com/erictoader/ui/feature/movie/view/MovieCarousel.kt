package com.erictoader.ui.feature.movie.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.feature.common.view.asset.AssetCarousel
import com.erictoader.ui.feature.movie.model.MovieCollection

@Composable
fun MovieCarousel(
    navController: NavController = rememberNavController(),
    movieCollection: MovieCollection
) = AssetCarousel(assetCollection = movieCollection, navController = navController)

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_MovieCarousel() {
    MovieCarousel(
        movieCollection = MovieCollection(
            items = listOf()
        )
    )
}
