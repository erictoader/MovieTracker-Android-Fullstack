package com.erictoader.ui.feature.common.model.ghost

import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.ui.feature.common.model.AssetModule
import com.erictoader.ui.feature.details.model.ghost.GhostCastModule
import com.erictoader.ui.feature.details.model.ghost.GhostReviewsModule
import com.erictoader.ui.feature.movie.model.ghost.GhostMovieModule
import kotlinx.parcelize.Parcelize

@Parcelize
open class GhostModule(private val ghostItems: List<GhostAsset>) : AssetModule("asdasdads", ghostItems) {

    companion object {
        fun of(moduleType: ModuleType): GhostModule {
            return when (moduleType) {
                ModuleType.VOD_MODULE -> GhostMovieModule
                ModuleType.CAST_MODULE -> GhostCastModule
                ModuleType.REVIEWS_MODULE -> GhostReviewsModule
                ModuleType.RECOMMENDED_MODULE -> GhostMovieModule
                else -> throw IllegalStateException()
            }
        }
    }
}
