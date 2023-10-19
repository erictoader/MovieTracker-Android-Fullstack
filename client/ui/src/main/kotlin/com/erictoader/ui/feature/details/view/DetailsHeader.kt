package com.erictoader.ui.feature.details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.erictoader.ui.feature.common.model.VodAsset
import com.erictoader.ui.feature.common.theme.fontSize
import com.erictoader.ui.feature.common.theme.spacing

@Composable
fun DetailsHeader(
    vodAsset: VodAsset,
) {
    Box {
        DetailsHeaderBackdrop(vodAsset = vodAsset)

        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth(0.6f),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            DetailsHeaderTitle(vodAsset.name)

            DetailsHeaderDescription(vodAsset.overview)
        }
    }
}


@Composable
private fun DetailsHeaderBackdrop(
    vodAsset: VodAsset
) {
    val density = LocalDensity.current
    var headerHeight by remember { mutableStateOf(0) }
    val radius = if (headerHeight < 0) 1f else headerHeight.toFloat() * 1.75f + 1

    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { headerHeight = it.height },
        model = vodAsset.backdropPath,
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )

    val brush = Brush.radialGradient(
        colors = listOf(Color.Black, Color.Transparent),
        center = Offset.Zero,
        radius = radius
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(with(density) { headerHeight.toDp() })
            .background(brush = brush)
    )
}

@Composable
private fun DetailsHeaderTitle(
    title: String?
) {
    val initialTextSize = MaterialTheme.fontSize.large

    var textStyle by remember { mutableStateOf(initialTextSize) }
    var readyToDraw by remember { mutableStateOf(false) }
    Text(
        text = title ?: "",
        fontSize = textStyle,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        maxLines = 2,
        modifier = Modifier.drawWithContent {
            if (readyToDraw) drawContent()
        },
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowHeight) textStyle *= 0.9
            else readyToDraw = true
        }
    )
}

@Composable
private fun DetailsHeaderDescription(
    overview: String?
) {
    val initialTextSize = MaterialTheme.fontSize.medium
    val smallestAcceptedTextSize = MaterialTheme.fontSize.small
    var textStyle by remember { mutableStateOf(initialTextSize) }
    var readyToDraw by remember { mutableStateOf(false) }
    Text(
        text = overview ?: "",
        fontSize = textStyle,
        color = Color.White.copy(0.7f),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .drawWithContent {
                if (readyToDraw) drawContent()
            },
        onTextLayout = { textLayoutResult ->
            if ((textLayoutResult.didOverflowHeight) && (textStyle > smallestAcceptedTextSize)) {
                textStyle *= 0.9
            } else readyToDraw = true
        }
    )
}
