package com.erictoader.data.remote.model

import com.erictoader.domain.feature.common.DomainModel

interface ModelMapper<M: DomainModel> {

    fun mapToDomainModel(): M
}
