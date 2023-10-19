package com.erictoader.movietrackerbackend.response.model

data class WatchlistModel(
    val id: Int,
    val userId: Int,
    val assetId: Int,
    val assetType: String,
    val createdAt: String
): Model
