package com.erictoader.domain.feature.series.usecase.abstraction

import com.erictoader.domain.feature.series.model.SeriesModuleDomain

interface GetSeriesModulesUseCase {

    suspend operator fun invoke(): List<SeriesModuleDomain>
}
