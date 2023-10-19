package com.erictoader.ui.feature.details.view

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
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.ui.feature.common.model.AssetDetails
import com.erictoader.ui.feature.common.model.AssetModule
import com.erictoader.ui.feature.common.model.VodAsset
import com.erictoader.ui.feature.common.model.ghost.GhostAssetDetails
import com.erictoader.ui.feature.common.model.ghost.GhostModule
import com.erictoader.ui.feature.common.theme.spacing
import com.erictoader.ui.feature.common.view.asset.AssetStripe
import com.erictoader.ui.feature.details.model.ghost.GhostWatchlistEntry
import com.erictoader.ui.feature.details.viewmodel.DetailsEvent
import com.erictoader.ui.feature.details.viewmodel.DetailsEvent.Companion.defaultModuleTypes
import com.erictoader.ui.feature.details.viewmodel.DetailsState
import com.erictoader.ui.feature.movie.model.Movie

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    vodAsset: VodAsset,
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit
) {
    vodAsset.id?.let { assetId ->
        var modules by rememberSaveable {
            mutableStateOf<List<AssetModule>>(defaultModuleTypes.map { GhostModule.of(it) })
        }
        var assetDetails by rememberSaveable { mutableStateOf<AssetDetails>(GhostAssetDetails()) }
        var watchlistStatus by remember { mutableStateOf<WatchlistEntryDomain?>(GhostWatchlistEntry) }
        var isScrollEnabled by rememberSaveable { mutableStateOf(false) }
        var isWatchlistButtonEnabled by rememberSaveable { mutableStateOf(false) }

        LazyColumn(
            modifier = modifier,
            userScrollEnabled = isScrollEnabled,
            contentPadding = PaddingValues(bottom = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            item {
                DetailsHeader(
                    vodAsset = vodAsset
                )
            }

            item {
                DetailsWatchlistStripe(
                    assetDetails = assetDetails,
                    watchlistStatus = watchlistStatus,
                    onClickWatchlistButton = {
                        isWatchlistButtonEnabled = false

                        watchlistStatus?.let {
                            onEvent(DetailsEvent.RemoveFromWatchlist(it.id))
                            return@DetailsWatchlistStripe
                        }

                        onEvent(DetailsEvent.AddToWatchlist(assetId, vodAsset.assetType))
                    },
                    isButtonEnabled = isWatchlistButtonEnabled
                )
            }

            items(modules) {
                AssetStripe(
                    navController = navController,
                    assetModule = it
                )
            }
        }

        LaunchedEffect(state) {
            when (state) {
                is DetailsState.Init -> {
                    onEvent(DetailsEvent.LoadDetails(assetId, vodAsset.assetType))
                }

                is DetailsState.ModulesLoaded -> {
                    modules = state.modules
                    isScrollEnabled = true
                }

                is DetailsState.DetailsLoaded -> {
                    assetDetails = state.details
                    onEvent(DetailsEvent.LoadWatchlistStatus(assetId, vodAsset.assetType))
                }

                is DetailsState.WatchlistStatusLoaded -> {
                    watchlistStatus = state.watchlistEntry
                    isWatchlistButtonEnabled = true
                    onEvent(DetailsEvent.LoadModules(assetId, vodAsset.assetType))
                }

                is DetailsState.AddedToWatchlist -> {
                    watchlistStatus = state.watchlistEntry
                    isWatchlistButtonEnabled = true
                }

                is DetailsState.RemovedFromWatchlist -> {
                    watchlistStatus = null
                    isWatchlistButtonEnabled = true
                }

                is DetailsState.Error -> Unit
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun Preview_DetailsScreen() {
    DetailsScreen(
        vodAsset = Movie(
            name = "Asset name",
            overview = "Long overview asset description about this piece of content",
            originalLanguage = "DE",
            popularity = 2.0,
            voteAverage = 4.0
        ),
        state = DetailsState.Init,
        onEvent = {}
    )
}
