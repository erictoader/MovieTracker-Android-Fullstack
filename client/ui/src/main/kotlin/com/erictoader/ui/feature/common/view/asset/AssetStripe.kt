package com.erictoader.ui.feature.common.view.asset

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.erictoader.ui.feature.common.model.AssetModule
import com.erictoader.ui.feature.common.model.ghost.GhostModule
import com.erictoader.ui.feature.common.theme.fontSize
import com.erictoader.ui.feature.common.theme.spacing
import com.erictoader.ui.feature.common.view.shimmerEffect

@Composable
fun <T : AssetModule> AssetStripe(
    navController: NavController,
    assetModule: T
) {
    if (assetModule.items.isEmpty()) return
    val isScrollEnabled = rememberSaveable(assetModule) { assetModule !is GhostModule }

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        if (assetModule is GhostModule) {
            Box(
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.small)
                    .width(MaterialTheme.spacing.XXL)
                    .height(MaterialTheme.spacing.medium)
                    .clip(RoundedCornerShape(50))
                    .shimmerEffect(),
            )
        } else {
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                text = assetModule.header,
                fontSize = MaterialTheme.fontSize.medium,
                fontWeight = FontWeight.Bold
            )
        }

        LazyRow(
            userScrollEnabled = isScrollEnabled,
            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(assetModule.items) {
                AssetCard(
                    navController = navController,
                    asset = it
                )
            }
        }
    }
}
