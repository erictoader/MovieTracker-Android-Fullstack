package com.erictoader.ui.feature.common.navigator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.erictoader.ui.feature.auth.view.AuthScreen
import com.erictoader.ui.feature.auth.viewmodel.AuthViewModel
import com.erictoader.ui.feature.common.model.VodAsset
import com.erictoader.ui.feature.common.navigator.bottomnav.BottomNavBar
import com.erictoader.ui.feature.common.navigator.bottomnav.BottomNavItem
import com.erictoader.ui.feature.details.view.DetailsScreen
import com.erictoader.ui.feature.details.viewmodel.DetailsViewModel
import com.erictoader.ui.feature.movie.view.MoviesScreen
import com.erictoader.ui.feature.movie.viewmodel.MoviesViewModel
import com.erictoader.ui.feature.series.view.SeriesScreen
import com.erictoader.ui.feature.series.viewmodel.SeriesViewModel
import com.erictoader.ui.feature.settings.view.SettingsScreen
import com.erictoader.ui.feature.splash.view.SplashScreen
import com.erictoader.ui.feature.splash.viewmodel.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenDestination.Intro.route
    ) {
        introGraph(navController)

        mainGraph(navController)
    }
}

private fun NavGraphBuilder.introGraph(navController: NavController) {
    navigation(
        route = ScreenDestination.Intro.route,
        startDestination = ScreenDestination.Intro.SplashScreen.route
    ) {
        composable(ScreenDestination.Intro.SplashScreen.route) {
            val viewModel = koinViewModel<SplashViewModel>()
            val state by viewModel.state
            SplashScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(ScreenDestination.Intro.AuthScreen.route) {
            val viewModel = koinViewModel<AuthViewModel>()
            val state by viewModel.state

            AuthScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}

private fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation(
        route = ScreenDestination.Main.route,
        startDestination = ScreenDestination.Main.MoviesScreen.route,
    ) {
        composable(ScreenDestination.Main.MoviesScreen.route) {
            val viewModel = koinViewModel<MoviesViewModel>()
            val state by viewModel.state

            FullscreenWithNavbar(
                navController = navController
            ) {
                MoviesScreen(
                    modifier = Modifier.padding(it),
                    navController = navController,
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
        composable(ScreenDestination.Main.SeriesScreen.route) {
            val viewModel = koinViewModel<SeriesViewModel>()
            val state by viewModel.state

            FullscreenWithNavbar(
                navController = navController
            ) {
                SeriesScreen(
                    modifier = Modifier.padding(it),
                    navController = navController,
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
        composable(ScreenDestination.Main.SettingsScreen.route) {

            FullscreenWithNavbar(
                navController = navController
            ) {
                SettingsScreen()
            }
        }
        composable(ScreenDestination.Main.DetailsScreen.route) { backStackEntry ->
            val viewModel = koinViewModel<DetailsViewModel>()
            val state by viewModel.state
            val vodAsset = backStackEntry.getArgument<VodAsset>()

            vodAsset?.let {
                FullscreenWithNavbar(
                    navController = navController
                ) {
                    DetailsScreen(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        vodAsset = vodAsset,
                        state = state,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FullscreenWithNavbar(
    navController: NavHostController,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(
                        name = "Movies",
                        destination = ScreenDestination.Main.MoviesScreen,
                        icon = Icons.Default.Movie
                    ),
                    BottomNavItem(
                        name = "Series",
                        destination = ScreenDestination.Main.SeriesScreen,
                        icon = Icons.Default.Tv
                    ),
                    BottomNavItem(
                        name = "Settings",
                        destination = ScreenDestination.Main.SettingsScreen,
                        icon = Icons.Default.Settings,
                    ),
                ),
                navController = navController,
                onItemClick = { navController.navigate(it.destination) }
            )
        },
        content = content
    )
}

sealed class ScreenDestination(
    override val name: String,
    vararg argumentPaths: String
) : Destination(name, *argumentPaths) {

    object Intro : ScreenDestination("intro") {
        object SplashScreen : ScreenDestination("splash")
        object AuthScreen : ScreenDestination("auth")
    }

    object Main : ScreenDestination("main") {
        object MoviesScreen : ScreenDestination("movies")
        object SeriesScreen : ScreenDestination("series")
        object SettingsScreen : ScreenDestination("settings")
        object DetailsScreen : ScreenDestination("details", argumentOf<VodAsset>())
    }
}
