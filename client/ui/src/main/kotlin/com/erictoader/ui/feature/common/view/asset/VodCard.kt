package com.erictoader.ui.feature.common.view.asset

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erictoader.ui.feature.common.model.VodAsset
import com.erictoader.ui.feature.common.model.ghost.GhostAsset
import com.erictoader.ui.feature.common.navigator.ScreenDestination
import com.erictoader.ui.feature.common.navigator.navigate
import com.erictoader.ui.feature.common.view.shimmerEffect

@Composable
fun VodCard(
    navController: NavController,
    vodAsset: VodAsset
) {
    if (vodAsset is GhostAsset) {
        Box(
            modifier = Modifier
                .width(CARD_WIDTH)
                .height(CARD_HEIGHT)
                .shimmerEffect()
        )
    } else {
        AsyncImage(
            modifier = Modifier
                .width(CARD_WIDTH)
                .height(CARD_HEIGHT)
                .clickable {
                    navController.navigate(ScreenDestination.Main.DetailsScreen, vodAsset)
                },
            model = vodAsset.posterPath,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

private val CARD_WIDTH = 120.dp
private val CARD_HEIGHT = 180.dp
