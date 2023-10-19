package com.erictoader.ui.feature.splash.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.erictoader.domain.feature.splash.usecase.abstraction.AttemptCacheLoginUseCase
import com.erictoader.ui.feature.common.util.DispatcherProvider
import com.erictoader.ui.feature.common.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

class SplashViewModel(
    private val attemptCacheLoginUseCase: AttemptCacheLoginUseCase,
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<SplashEvent>(dispatcherProvider) {

    var state = mutableStateOf<SplashState>(SplashState.Init)
        private set

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Caught exception ${exception.message}")
        state.value = SplashState.Error
    }

    override fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.AttemptCacheLogin -> attemptCachedLogin()
        }
    }

    private fun attemptCachedLogin() =
        launchOnIO(exceptionHandler) {
            attemptCacheLoginUseCase()
            state.value = SplashState.Success
        }

    private companion object {
        private val TAG = SplashViewModel::class.java.simpleName
    }
}
