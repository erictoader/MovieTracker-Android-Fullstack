package com.erictoader.ui.feature.common.view.asset

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erictoader.ui.feature.common.model.AssetCollection
import com.erictoader.ui.feature.common.model.ghost.GhostAsset
import com.erictoader.ui.feature.common.theme.spacing
import com.erictoader.ui.feature.common.view.shimmerEffect
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T : AssetCollection> AssetCarousel(
    navController: NavController,
    assetCollection: T
) {
    val pageCount = Int.MAX_VALUE
    val pagerState = rememberPagerState()

    Box {
        HorizontalPager(
            pageCount = pageCount,
            state = pagerState
        ) {
            val movie = assetCollection.items[it % assetCollection.items.size]

            if (assetCollection is GhostAsset) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(ASSET_HEIGHT)
                        .shimmerEffect()
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .height(ASSET_HEIGHT),
                    model = movie.backdropPath,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            }
        }

        if (assetCollection !is GhostAsset) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        vertical = MaterialTheme.spacing.medium,
                        horizontal = MaterialTheme.spacing.large
                    )
            ) {
                assetCollection.items.forEachIndexed { index, _ ->
                    val currentPagerMovieIndex = pagerState.currentPage % assetCollection.items.size
                    Surface(
                        modifier = Modifier.padding(end = MaterialTheme.spacing.extraSmall),
                        shape = CircleShape,
                        color = if (currentPagerMovieIndex == index) Color.White
                        else Color.Gray.copy(alpha = 0.75f)
                    ) {
                        Box(Modifier.size(MaterialTheme.spacing.small))
                    }
                }
            }

            LaunchedEffect(pagerState) {
                while (true) {
                    delay(5000)
                    pagerState.animateScrollToPage(pagerState.currentPage.inc())
                }
            }
        }
    }
}

private val ASSET_HEIGHT = 220.dp
