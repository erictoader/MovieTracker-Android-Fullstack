package com.erictoader.ui.feature.splash.viewmodel

import com.erictoader.ui.feature.common.viewmodel.State

sealed class SplashState : State {

    object Init : SplashState()
    object Success : SplashState()
    object Error : SplashState()
}
