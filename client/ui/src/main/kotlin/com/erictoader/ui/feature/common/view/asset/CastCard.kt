package com.erictoader.ui.feature.common.view.asset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erictoader.ui.feature.common.theme.spacing
import com.erictoader.ui.feature.common.view.shimmerEffect
import com.erictoader.ui.feature.details.model.Cast
import com.erictoader.ui.feature.details.model.ghost.GhostCast

@Composable
fun CastCard(
    navController: NavController,
    cast: Cast
) {
    if (cast is GhostCast) {
        Box(
            modifier = Modifier
                .width(CARD_WIDTH)
                .height(CARD_HEIGHT)
                .clip(CircleShape)
                .shimmerEffect()
        )
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(CARD_WIDTH)
                    .height(CARD_HEIGHT)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.onBackground),
                model = cast.profilePath,
                fallback = rememberVectorPainter(image = Icons.Default.Person),
                error = rememberVectorPainter(image = Icons.Default.Person),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Text(
                modifier = Modifier.width(CARD_WIDTH),
                text = cast.name ?: "",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2
            )

            Text(
                modifier = Modifier.width(CARD_WIDTH),
                text = cast.character ?: "",
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

private val CARD_WIDTH = 90.dp
private val CARD_HEIGHT = 90.dp
