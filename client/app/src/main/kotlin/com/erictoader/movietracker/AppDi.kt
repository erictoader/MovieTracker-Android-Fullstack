package com.erictoader.movietracker

import org.koin.dsl.module
import com.erictoader.data.dataModule
import com.erictoader.domain.domainModule
import com.erictoader.ui.uiModule

val appModule = module {
    includes(listOf(dataModule, domainModule, uiModule))
}
