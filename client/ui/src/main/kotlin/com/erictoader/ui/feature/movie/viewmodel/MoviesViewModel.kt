package com.erictoader.ui.feature.movie.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.erictoader.domain.feature.movie.usecase.abstraction.GetMovieCarouselUseCase
import com.erictoader.domain.feature.movie.usecase.abstraction.GetMovieModulesUseCase
import com.erictoader.ui.feature.common.util.DispatcherProvider
import com.erictoader.ui.feature.common.viewmodel.BaseViewModel
import com.erictoader.ui.feature.movie.mapper.mapToUiModel
import kotlinx.coroutines.CoroutineExceptionHandler

class MoviesViewModel(
    dispatcherProvider: DispatcherProvider,
    private val getMovieModulesUseCase: GetMovieModulesUseCase,
    private val getMovieCarouselUseCase: GetMovieCarouselUseCase
) : BaseViewModel<MoviesEvent>(dispatcherProvider) {

    var state = mutableStateOf<MoviesState>(MoviesState.Init)
        private set

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Caught exception ${exception.message}")
        state.value = MoviesState.Error
    }

    override fun onEvent(event: MoviesEvent) {
        when (event) {
            MoviesEvent.LoadModules -> loadHomeModules()
            MoviesEvent.LoadCarousel -> loadHomeCarousel()
        }
    }

    private fun loadHomeModules() =
        launchOnIO(exceptionHandler) {
            val movieModules = getMovieModulesUseCase().map { it.mapToUiModel() }
            state.value = MoviesState.ModulesLoaded(movieModules)
        }

    private fun loadHomeCarousel() =
        launchOnIO(exceptionHandler) {
            val movieCollection = getMovieCarouselUseCase().mapToUiModel()
            state.value = MoviesState.CarouselLoaded(movieCollection)
        }

    private companion object {
        private val TAG = MoviesViewModel::class.java.simpleName
    }
}
