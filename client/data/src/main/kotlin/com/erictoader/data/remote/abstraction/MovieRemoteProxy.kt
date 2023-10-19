package com.erictoader.data.remote.abstraction

import com.erictoader.data.remote.model.credits.CreditsData
import com.erictoader.data.remote.model.movie.MovieBasicData
import com.erictoader.data.remote.model.details.MovieDetailsData
import com.erictoader.data.remote.model.review.ReviewsData

interface MovieRemoteProxy {

    suspend fun getMovieCategory(category: String): List<MovieBasicData>

    suspend fun getMovieDetails(movieId: Int): MovieDetailsData

    suspend fun getMovieCredits(movieId: Int): CreditsData

    suspend fun getMovieReviews(movieId: Int): ReviewsData

    suspend fun getMovieRecommendations(movieId: Int): List<MovieBasicData>
}
