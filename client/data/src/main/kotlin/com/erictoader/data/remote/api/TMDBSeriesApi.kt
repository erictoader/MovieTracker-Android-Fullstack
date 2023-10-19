package com.erictoader.data.remote.api

import com.erictoader.data.remote.model.credits.CreditsData
import com.erictoader.data.remote.model.details.SeriesDetailsData
import com.erictoader.data.remote.model.review.ReviewsData
import com.erictoader.data.remote.model.series.SeriesData
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBSeriesApi {

    @GET("tv/{category}")
    suspend fun getSeriesCategory(
        @Path("category") category: String
    ): SeriesData

    @GET("tv/{tv_id}")
    suspend fun getSeriesDetails(
        @Path("tv_id") seriesId: Int
    ): SeriesDetailsData

    @GET("tv/{tv_id}/credits")
    suspend fun getSeriesCredits(
        @Path("tv_id") movieId: Int
    ): CreditsData

    @GET("tv/{tv_id}/reviews")
    suspend fun getSeriesReviews(
        @Path("tv_id") movieId: Int
    ): ReviewsData

    @GET("tv/{tv_id}/recommendations")
    suspend fun getSeriesRecommendations(
        @Path("tv_id") seriesId: Int
    ): SeriesData
}
