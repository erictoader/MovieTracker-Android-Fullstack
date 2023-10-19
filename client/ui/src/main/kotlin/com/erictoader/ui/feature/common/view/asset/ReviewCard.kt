package com.erictoader.ui.feature.common.view.asset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.erictoader.ui.feature.common.model.ghost.GhostAsset
import com.erictoader.ui.feature.common.theme.spacing
import com.erictoader.ui.feature.common.view.shimmerEffect
import com.erictoader.ui.feature.details.model.Review

@Composable
fun ReviewCard(
    navController: NavController,
    review: Review
) {
    if (review is GhostAsset) {
        Box(
            modifier = Modifier
                .width(CARD_WIDTH)
                .height(CARD_HEIGHT)
                .shimmerEffect()
        )
    } else {
        val scrollState = rememberScrollState()
        Box {
            Column(
                modifier = Modifier
                    .width(CARD_WIDTH)
                    .height(CARD_HEIGHT)
                    .background(MaterialTheme.colors.primary)
                    .padding(MaterialTheme.spacing.small)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            ) {
                Text(
                    modifier = Modifier.width(CARD_WIDTH),
                    text = review.author ?: "Unknown User",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                Text(
                    modifier = Modifier.width(CARD_WIDTH),
                    text = review.content ?: "",
                    textAlign = TextAlign.Start,
                )
            }

            var layoutHeight by rememberSaveable { mutableStateOf(0) }
            var percentageScrolled = scrollState.value.toFloat() / scrollState.maxValue.toFloat()
            if (percentageScrolled < 0f) percentageScrolled = 0f
            if (percentageScrolled > 1f) percentageScrolled = 1f
            if (percentageScrolled.isNaN()) percentageScrolled = 0f

            val gradientStartY = rememberSaveable(layoutHeight, percentageScrolled) {
                minOf(
                    (percentageScrolled + 0.3f) * layoutHeight - 1,
                    layoutHeight.toFloat() - layoutHeight.toFloat() / 20
                )
            }

            val brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Black),
                startY = gradientStartY
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onSizeChanged { layoutHeight = it.height }
                    .width(CARD_WIDTH)
                    .height(CARD_HEIGHT)
                    .background(brush = brush)
            )
        }
    }
}

private val CARD_WIDTH = 200.dp
private val CARD_HEIGHT = 220.dp
