package com.erictoader.ui.feature.splash.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.erictoader.ui.R
import com.erictoader.ui.feature.common.navigator.ScreenDestination
import com.erictoader.ui.feature.common.navigator.navigate
import com.erictoader.ui.feature.splash.viewmodel.SplashEvent
import com.erictoader.ui.feature.splash.viewmodel.SplashState

@Composable
fun SplashScreen(
    navController: NavController = rememberNavController(),
    state: SplashState,
    onEvent: (SplashEvent) -> Unit
) {
    var isAnimationFinished by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.splash_animation)
        )
        val progress by animateLottieCompositionAsState(composition)

        LottieAnimation(
            composition = composition,
            iterations = 1
        )

        if (progress == 1f) isAnimationFinished = true
    }

    LaunchedEffect(Unit) { onEvent(SplashEvent.AttemptCacheLogin) }

    LaunchedEffect(isAnimationFinished) {
        if (isAnimationFinished) {
            when (state) {
                is SplashState.Init -> Unit

                is SplashState.Success -> {
                    navController.navigate(ScreenDestination.Main)
                }

                is SplashState.Error -> {
                    navController.navigate(ScreenDestination.Intro.AuthScreen)
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_SplashScreen() {
    SplashScreen(
        state = SplashState.Init,
        onEvent = { }
    )
}
