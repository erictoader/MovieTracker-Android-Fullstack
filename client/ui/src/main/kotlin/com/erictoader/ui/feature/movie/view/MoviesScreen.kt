package com.erictoader.ui.feature.movie.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.feature.common.model.AssetModule
import com.erictoader.ui.feature.common.theme.spacing
import com.erictoader.ui.feature.movie.model.MovieCollection
import com.erictoader.ui.feature.movie.model.ghost.GhostMovieCollection
import com.erictoader.ui.feature.movie.model.ghost.GhostMovieModule
import com.erictoader.ui.feature.movie.viewmodel.MoviesEvent
import com.erictoader.ui.feature.movie.viewmodel.MoviesState

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    state: MoviesState,
    onEvent: (MoviesEvent) -> Unit
) {
    var movieCollection by rememberSaveable { mutableStateOf<MovieCollection>(GhostMovieCollection) }
    var movieModules by remember { mutableStateOf(List<AssetModule>(5) { GhostMovieModule }) }
    var isScrollEnabled by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier,
        userScrollEnabled = isScrollEnabled,
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        item {
            MovieCarousel(
                navController = navController,
                movieCollection = movieCollection
            )
        }

        items(movieModules) {
            MovieStripe(
                navController = navController,
                movieModule = it
            )
        }
    }

    LaunchedEffect(state) {
        when (state) {
            is MoviesState.Init -> {
                onEvent(MoviesEvent.LoadCarousel)
            }

            is MoviesState.ModulesLoaded -> {
                movieModules = state.modules
                isScrollEnabled = true
            }

            is MoviesState.CarouselLoaded -> {
                movieCollection = state.collection
                onEvent(MoviesEvent.LoadModules)
            }

            is MoviesState.Error -> Unit
        }
    }
}


@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_MoviesScreen() {
    MoviesScreen(
        state = MoviesState.Init,
        onEvent = { }
    )
}
