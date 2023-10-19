package com.erictoader.ui.feature.series.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.erictoader.ui.feature.common.theme.spacing
import com.erictoader.ui.feature.series.model.SeriesCollection
import com.erictoader.ui.feature.series.model.SeriesModule
import com.erictoader.ui.feature.series.model.ghost.GhostSeriesCollection
import com.erictoader.ui.feature.series.model.ghost.GhostSeriesModule
import com.erictoader.ui.feature.series.viewmodel.SeriesEvent
import com.erictoader.ui.feature.series.viewmodel.SeriesState

@Composable
fun SeriesScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    state: SeriesState,
    onEvent: (SeriesEvent) -> Unit
) {
    var seriesModules by rememberSaveable {
        mutableStateOf(List<SeriesModule>(5) { GhostSeriesModule })
    }
    var seriesCollection by rememberSaveable {
        mutableStateOf<SeriesCollection>(GhostSeriesCollection)
    }
    var isScrollEnabled by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier,
        userScrollEnabled = isScrollEnabled,
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        item {
            SeriesCarousel(
                navController = navController,
                seriesCollection = seriesCollection
            )
        }

        items(seriesModules) {
            SeriesStripe(
                navController = navController,
                seriesModule = it
            )
        }
    }

    LaunchedEffect(state) {
        when (state) {
            is SeriesState.Init -> {
                onEvent(SeriesEvent.LoadCarousel)
            }

            is SeriesState.CarouselLoaded -> {
                seriesCollection = state.collection
                onEvent(SeriesEvent.LoadModules)
            }

            is SeriesState.ModulesLoaded -> {
                seriesModules = state.modules
                isScrollEnabled = true
            }

            is SeriesState.Error -> Unit
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_SeriesScreen() {
    SeriesScreen(
        state = SeriesState.Init,
        onEvent = { }
    )
}
