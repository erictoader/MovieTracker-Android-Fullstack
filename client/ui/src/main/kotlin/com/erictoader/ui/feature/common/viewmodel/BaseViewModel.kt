package com.erictoader.ui.feature.common.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.erictoader.ui.feature.common.util.DispatcherProvider

abstract class BaseViewModel<E: Event>(
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val baseExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Unprocessed Exception", exception )
    }

    abstract fun onEvent(event: E)

    protected fun launchOnIO(
        exceptionHandler: CoroutineExceptionHandler = baseExceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ) =
        viewModelScope.launch(
            context = dispatcherProvider.getIO() + exceptionHandler,
            block = block
        )

    private companion object {
        private val TAG = BaseViewModel::class.java.simpleName
    }
}
