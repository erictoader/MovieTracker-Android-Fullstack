package com.erictoader.data.remote.abstraction

import com.erictoader.data.remote.model.credits.CreditsData
import com.erictoader.data.remote.model.details.SeriesDetailsData
import com.erictoader.data.remote.model.review.ReviewsData
import com.erictoader.data.remote.model.series.SeriesBasicData

interface SeriesRemoteProxy {

    suspend fun getSeriesCategory(category: String): List<SeriesBasicData>

    suspend fun getSeriesDetails(seriesId: Int): SeriesDetailsData

    suspend fun getSeriesCredits(seriesId: Int): CreditsData

    suspend fun getSeriesReviews(seriesId: Int): ReviewsData

    suspend fun getSeriesRecommendations(seriesId: Int): List<SeriesBasicData>
}
