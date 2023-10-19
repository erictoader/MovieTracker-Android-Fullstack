package com.erictoader.ui.feature.details.viewmodel

import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.ui.feature.common.viewmodel.Event

sealed class DetailsEvent : Event {

    data class LoadDetails(
        val assetId: Int,
        val assetType: AssetType
    ) : DetailsEvent()

    data class LoadModules(
        val assetId: Int,
        val assetType: AssetType,
        val moduleTypes: List<ModuleType> = defaultModuleTypes
    ) : DetailsEvent()

    data class LoadWatchlistStatus(
        val assetId: Int,
        val assetType: AssetType
    ) : DetailsEvent()

    data class AddToWatchlist(
        val assetId: Int,
        val assetType: AssetType
    ) : DetailsEvent()

    data class RemoveFromWatchlist(val entryId: Int) : DetailsEvent()

    companion object {
        val defaultModuleTypes = listOf(
            ModuleType.CAST_MODULE,
            ModuleType.REVIEWS_MODULE,
            ModuleType.RECOMMENDED_MODULE
        )
    }
}
