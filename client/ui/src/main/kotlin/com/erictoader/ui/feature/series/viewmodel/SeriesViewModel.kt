package com.erictoader.ui.feature.series.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.erictoader.domain.feature.series.usecase.abstraction.GetSeriesCarouselUseCase
import com.erictoader.domain.feature.series.usecase.abstraction.GetSeriesModulesUseCase
import com.erictoader.ui.feature.common.util.DispatcherProvider
import com.erictoader.ui.feature.common.viewmodel.BaseViewModel
import com.erictoader.ui.feature.series.mapper.mapToUiModel
import kotlinx.coroutines.CoroutineExceptionHandler

class SeriesViewModel(
    dispatcherProvider: DispatcherProvider,
    private val getSeriesModulesUseCase: GetSeriesModulesUseCase,
    private val getSeriesCarouselUseCase: GetSeriesCarouselUseCase
) : BaseViewModel<SeriesEvent>(dispatcherProvider) {

    var state = mutableStateOf<SeriesState>(SeriesState.Init)
        private set

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Caught exception ${exception.message}")
        state.value = SeriesState.Error
    }

    override fun onEvent(event: SeriesEvent) {
        when (event) {
            SeriesEvent.LoadModules -> loadHomeModules()
            SeriesEvent.LoadCarousel -> loadHomeCarousel()
        }
    }

    private fun loadHomeModules() =
        launchOnIO(exceptionHandler) {
            val seriesModules = getSeriesModulesUseCase().map { it.mapToUiModel() }
            state.value = SeriesState.ModulesLoaded(seriesModules)
        }

    private fun loadHomeCarousel() =
        launchOnIO(exceptionHandler) {
            val seriesCollection = getSeriesCarouselUseCase().mapToUiModel()
            state.value = SeriesState.CarouselLoaded(seriesCollection)
        }

    private companion object {
        private val TAG = SeriesViewModel::class.java.simpleName
    }
}
