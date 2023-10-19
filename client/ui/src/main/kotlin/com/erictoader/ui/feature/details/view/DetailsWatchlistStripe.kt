package com.erictoader.ui.feature.details.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.ui.feature.common.model.AssetDetails
import com.erictoader.ui.feature.common.model.ghost.GhostAssetDetails
import com.erictoader.ui.feature.common.theme.fontSize
import com.erictoader.ui.feature.common.theme.spacing
import com.erictoader.ui.feature.common.view.GhostText
import com.erictoader.ui.feature.common.view.shimmerEffect
import com.erictoader.ui.feature.details.model.MovieDetails
import com.erictoader.ui.feature.details.model.SeriesDetails
import com.erictoader.ui.feature.details.model.ghost.GhostWatchlistEntry

@Composable
fun DetailsWatchlistStripe(
    assetDetails: AssetDetails,
    watchlistStatus: WatchlistEntryDomain?,
    onClickWatchlistButton: () -> Unit,
    isButtonEnabled: Boolean
) {
    Row(
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            DetailsHeaderRuntime(assetDetails = assetDetails)
            DetailsHeaderGenres(assetDetails = assetDetails)
        }

        Spacer(modifier = Modifier.weight(1f))

        WatchlistButton(
            onClick = onClickWatchlistButton,
            watchlistStatus = watchlistStatus,
            isButtonEnabled = isButtonEnabled
        )
    }
}

@Composable
private fun DetailsHeaderRuntime(
    assetDetails: AssetDetails
) {
    val runtime = when (assetDetails) {
        is MovieDetails -> {
            val time = assetDetails.runtime ?: 0
            val hours = (time) / 60
            val minutes = time - hours * 60
            "${hours}h ${minutes}min"
        }

        is SeriesDetails -> {
            val seasons = assetDetails.numberOfSeasons ?: 0
            val episodes = assetDetails.numberOfEpisodes ?: 0
            "$seasons seasons, $episodes episodes"
        }

        else -> ""
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null
        )

        if (assetDetails is GhostAssetDetails) GhostText()
        else Text(
            text = runtime,
            fontSize = MaterialTheme.fontSize.smallMedium,
            color = Color.White.copy(0.9f)
        )
    }
}

@Composable
private fun DetailsHeaderGenres(
    assetDetails: AssetDetails
) {
    val genres = assetDetails.genres?.take(4)?.joinToString(", ")

    Row(
        modifier = Modifier.fillMaxWidth(0.5f),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocalMovies,
            contentDescription = null
        )

        if (assetDetails is GhostAssetDetails) GhostText(width = MaterialTheme.spacing.huge)
        else Text(
            text = genres ?: "",
            maxLines = 2,
            fontSize = MaterialTheme.fontSize.smallMedium,
            color = Color.White.copy(0.9f),
        )
    }
}

@Composable
private fun WatchlistButton(
    onClick: () -> Unit,
    watchlistStatus: WatchlistEntryDomain?,
    isButtonEnabled: Boolean
) {

    if (watchlistStatus is GhostWatchlistEntry) {
        Box(
            modifier = Modifier
                .height(MaterialTheme.spacing.XL)
                .fillMaxWidth(0.75f)
                .clip(RoundedCornerShape(MaterialTheme.spacing.extraSmall))
                .shimmerEffect()
        )
    } else {
        val isAddedToWatchlist = watchlistStatus != null

        val icon = if (isAddedToWatchlist) Icons.Default.Done else Icons.Default.WatchLater
        val buttonText = if (isAddedToWatchlist) "Added to Watchlist" else "Add to Watchlist"
        val buttonColor = (if (isAddedToWatchlist) Color.Green else Color.Yellow).copy(0.5f)

        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(MaterialTheme.spacing.XL),
            onClick = onClick,
            enabled = isButtonEnabled,
            border = BorderStroke(MaterialTheme.spacing.minuscule, Color.White),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor,
                contentColor = Color.White.copy(0.8f),
                disabledBackgroundColor = Color.Gray.copy(0.25f),
                disabledContentColor = Color.White.copy(0.8f)
            )
        ) {
            Text(text = buttonText, fontSize = 14.sp)

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            Icon(imageVector = icon, contentDescription = null)
        }
    }
}
