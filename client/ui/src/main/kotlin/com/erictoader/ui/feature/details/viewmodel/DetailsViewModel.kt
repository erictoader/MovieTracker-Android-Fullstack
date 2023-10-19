package com.erictoader.ui.feature.details.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.domain.feature.details.usecase.abstraction.AddAssetToWatchlistUseCase
import com.erictoader.domain.feature.details.usecase.abstraction.GetAssetDetailsUseCase
import com.erictoader.domain.feature.details.usecase.abstraction.GetAssetWatchlistStatusUseCase
import com.erictoader.domain.feature.details.usecase.abstraction.GetDetailModulesUseCase
import com.erictoader.domain.feature.details.usecase.abstraction.RemoveAssetFromWatchlistUseCase
import com.erictoader.ui.feature.common.util.DispatcherProvider
import com.erictoader.ui.feature.common.viewmodel.BaseViewModel
import com.erictoader.ui.feature.details.mapper.mapToUiModel

class DetailsViewModel(
    dispatcherProvider: DispatcherProvider,
    private val getAssetDetailsUseCase: GetAssetDetailsUseCase,
    private val getDetailModulesUseCase: GetDetailModulesUseCase,
    private val getAssetWatchlistStatusUseCase: GetAssetWatchlistStatusUseCase,
    private val addAssetToWatchlistUseCase: AddAssetToWatchlistUseCase,
    private val removeAssetFromWatchlistUseCase: RemoveAssetFromWatchlistUseCase
) : BaseViewModel<DetailsEvent>(dispatcherProvider) {

    var state = mutableStateOf<DetailsState>(DetailsState.Init)
        private set

    override fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.LoadDetails -> loadDetails(
                event.assetId,
                event.assetType
            )

            is DetailsEvent.LoadModules -> loadModules(
                event.assetId,
                event.assetType,
                event.moduleTypes
            )

            is DetailsEvent.LoadWatchlistStatus -> loadWatchlistStatus(
                event.assetId,
                event.assetType
            )

            is DetailsEvent.AddToWatchlist -> addToWatchlist(
                event.assetId,
                event.assetType
            )

            is DetailsEvent.RemoveFromWatchlist -> removeFromWatchlist(event.entryId)
        }
    }

    private fun loadDetails(assetId: Int, assetType: AssetType) =
        launchOnIO {
            val assetDetails = getAssetDetailsUseCase(assetId, assetType)
            state.value = DetailsState.DetailsLoaded(assetDetails.mapToUiModel())
        }

    private fun loadModules(assetId: Int, assetType: AssetType, moduleTypes: List<ModuleType>) =
        launchOnIO {
            val modules = getDetailModulesUseCase(assetId, assetType, moduleTypes)
            state.value = DetailsState.ModulesLoaded(modules.map { it.mapToUiModel() })
        }

    private fun loadWatchlistStatus(assetId: Int, assetType: AssetType) =
        launchOnIO {
            val watchlistStatus = getAssetWatchlistStatusUseCase(assetId, assetType)
            state.value = DetailsState.WatchlistStatusLoaded(watchlistStatus)
        }

    private fun addToWatchlist(assetId: Int, assetType: AssetType) =
        launchOnIO {
            val watchlistStatus = addAssetToWatchlistUseCase(assetId, assetType)
            state.value = DetailsState.AddedToWatchlist(watchlistStatus)
        }

    private fun removeFromWatchlist(entryId: Int) =
        launchOnIO {
            removeAssetFromWatchlistUseCase(entryId)
            state.value = DetailsState.RemovedFromWatchlist
        }
}
