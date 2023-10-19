package com.erictoader.ui.feature.common.navigator

interface NavigationArgument {

    fun getArgumentName(): String {
        return this::class.simpleName ?: ""
    }
}
