package com.erictoader.movietrackerbackend.entity

import com.erictoader.movietrackerbackend.response.model.Model

interface EntityMapper<M: Model> {

    fun mapToModel(): M
}