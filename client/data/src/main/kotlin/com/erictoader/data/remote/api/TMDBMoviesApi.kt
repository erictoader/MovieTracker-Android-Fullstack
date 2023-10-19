package com.erictoader.data.remote.api

import com.erictoader.data.remote.model.credits.CreditsData
import com.erictoader.data.remote.model.details.MovieDetailsData
import com.erictoader.data.remote.model.movie.MoviesData
import com.erictoader.data.remote.model.review.ReviewsData
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBMoviesApi {

    @GET("movie/{category}")
    suspend fun getMovieCategory(
        @Path("category") category: String
    ): MoviesData

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsData

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ): CreditsData

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int
    ): ReviewsData

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movie_id") movieId: Int
    ): MoviesData
}
