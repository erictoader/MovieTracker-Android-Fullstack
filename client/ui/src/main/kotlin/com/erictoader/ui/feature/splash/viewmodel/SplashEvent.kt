package com.erictoader.ui.feature.splash.viewmodel

import com.erictoader.ui.feature.common.viewmodel.Event

sealed class SplashEvent : Event {

    object AttemptCacheLogin : SplashEvent()
}
