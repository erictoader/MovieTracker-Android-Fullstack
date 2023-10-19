package com.erictoader.domain

import com.erictoader.domain.feature.auth.usecase.abstraction.LoginUseCase
import com.erictoader.domain.feature.auth.usecase.abstraction.RegisterUseCase
import com.erictoader.domain.feature.auth.usecase.concrete.LoginUseCaseImpl
import com.erictoader.domain.feature.auth.usecase.concrete.RegisterUseCaseImpl
import com.erictoader.domain.feature.details.usecase.abstraction.AddAssetToWatchlistUseCase
import com.erictoader.domain.feature.details.usecase.abstraction.GetAssetDetailsUseCase
import com.erictoader.domain.feature.details.usecase.abstraction.GetAssetWatchlistStatusUseCase
import com.erictoader.domain.feature.details.usecase.abstraction.GetDetailModulesUseCase
import com.erictoader.domain.feature.details.usecase.abstraction.RemoveAssetFromWatchlistUseCase
import com.erictoader.domain.feature.details.usecase.concrete.AddAssetToWatchlistUseCaseImpl
import com.erictoader.domain.feature.details.usecase.concrete.GetAssetDetailsUseCaseImpl
import com.erictoader.domain.feature.details.usecase.concrete.GetAssetWatchlistStatusUseCaseImpl
import com.erictoader.domain.feature.details.usecase.concrete.GetDetailModulesUseCaseImpl
import com.erictoader.domain.feature.details.usecase.concrete.RemoveAssetFromWatchlistUseCaseImpl
import com.erictoader.domain.feature.movie.usecase.abstraction.GetMovieCarouselUseCase
import com.erictoader.domain.feature.movie.usecase.abstraction.GetMovieModulesUseCase
import com.erictoader.domain.feature.movie.usecase.concrete.GetMovieCarouselUseCaseImpl
import com.erictoader.domain.feature.movie.usecase.concrete.GetMovieModulesUseCaseImpl
import com.erictoader.domain.feature.series.usecase.abstraction.GetSeriesCarouselUseCase
import com.erictoader.domain.feature.series.usecase.abstraction.GetSeriesModulesUseCase
import com.erictoader.domain.feature.series.usecase.concrete.GetSeriesCarouselUseCaseImpl
import com.erictoader.domain.feature.series.usecase.concrete.GetSeriesModulesUseCaseImpl
import com.erictoader.domain.feature.splash.usecase.abstraction.AttemptCacheLoginUseCase
import com.erictoader.domain.feature.splash.usecase.concrete.AttemptCacheLoginUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<AttemptCacheLoginUseCase> { AttemptCacheLoginUseCaseImpl(get()) }

    factory<LoginUseCase> { LoginUseCaseImpl(get()) }

    factory<RegisterUseCase> { RegisterUseCaseImpl(get()) }

    factory<GetMovieModulesUseCase> { GetMovieModulesUseCaseImpl(get(), get()) }

    factory<GetMovieCarouselUseCase> { GetMovieCarouselUseCaseImpl(get()) }

    factory<GetSeriesModulesUseCase> { GetSeriesModulesUseCaseImpl(get(), get()) }

    factory<GetSeriesCarouselUseCase> { GetSeriesCarouselUseCaseImpl(get()) }

    factory<GetAssetDetailsUseCase> { GetAssetDetailsUseCaseImpl(get(), get()) }

    factory<GetDetailModulesUseCase> { GetDetailModulesUseCaseImpl(get(), get()) }

    factory<GetAssetWatchlistStatusUseCase> { GetAssetWatchlistStatusUseCaseImpl(get()) }

    factory<AddAssetToWatchlistUseCase> { AddAssetToWatchlistUseCaseImpl(get()) }

    factory<RemoveAssetFromWatchlistUseCase> { RemoveAssetFromWatchlistUseCaseImpl(get()) }
}
