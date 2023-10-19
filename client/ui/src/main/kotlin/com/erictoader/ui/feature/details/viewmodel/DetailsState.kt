package com.erictoader.ui.feature.details.viewmodel

import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.ui.feature.common.model.AssetDetails
import com.erictoader.ui.feature.common.model.AssetModule

sealed class DetailsState {

    object Init : DetailsState()
    data class DetailsLoaded(val details: AssetDetails): DetailsState()
    data class ModulesLoaded(val modules: List<AssetModule>) : DetailsState()
    data class WatchlistStatusLoaded(val watchlistEntry: WatchlistEntryDomain?) : DetailsState()
    data class AddedToWatchlist(val watchlistEntry: WatchlistEntryDomain?): DetailsState()
    object RemovedFromWatchlist: DetailsState()
    object Error : DetailsState()
}
