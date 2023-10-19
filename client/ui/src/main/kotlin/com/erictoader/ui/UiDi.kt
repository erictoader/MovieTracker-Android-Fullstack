package com.erictoader.ui

import com.erictoader.ui.feature.auth.viewmodel.AuthViewModel
import com.erictoader.ui.feature.common.util.DispatcherProvider
import com.erictoader.ui.feature.common.util.DispatcherProviderImpl
import com.erictoader.ui.feature.details.viewmodel.DetailsViewModel
import com.erictoader.ui.feature.movie.viewmodel.MoviesViewModel
import com.erictoader.ui.feature.series.viewmodel.SeriesViewModel
import com.erictoader.ui.feature.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }

    viewModel { SplashViewModel(get(), get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { MoviesViewModel(get(), get(), get()) }
    viewModel { SeriesViewModel(get(), get(), get()) }
    viewModel { DetailsViewModel(get(), get(), get(), get(), get(), get()) }
}
