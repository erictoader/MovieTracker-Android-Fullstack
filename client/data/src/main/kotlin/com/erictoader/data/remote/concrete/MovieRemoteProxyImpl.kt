package com.erictoader.data.remote.concrete

import com.erictoader.data.remote.model.credits.CreditsData
import com.erictoader.data.remote.model.details.MovieDetailsData
import com.erictoader.data.remote.model.movie.MovieBasicData
import com.erictoader.data.remote.model.movie.MoviesData
import com.erictoader.data.remote.model.review.ReviewsData
import com.erictoader.data.remote.abstraction.MovieRemoteProxy
import com.erictoader.data.remote.api.TMDBMoviesApi

class MovieRemoteProxyImpl(
    private val tmdbMoviesApi: TMDBMoviesApi
) : MovieRemoteProxy {

    override suspend fun getMovieCategory(category: String): List<MovieBasicData> =
        tmdbMoviesApi.getMovieCategory(category).also { completeImagePaths(it) }.results ?: listOf()

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsData =
        tmdbMoviesApi.getMovieDetails(movieId).also {
            it.posterPath = IMAGE_PATH_COMPLETION + it.posterPath
            it.backdropPath = IMAGE_PATH_COMPLETION + it.backdropPath
        }

    override suspend fun getMovieCredits(movieId: Int): CreditsData =
        tmdbMoviesApi.getMovieCredits(movieId).also {
            it.cast?.forEach { castMember ->
                castMember.profilePath = IMAGE_PATH_COMPLETION + castMember.profilePath
            }
        }

    override suspend fun getMovieReviews(movieId: Int): ReviewsData =
        tmdbMoviesApi.getMovieReviews(movieId)

    override suspend fun getMovieRecommendations(movieId: Int): List<MovieBasicData> =
        tmdbMoviesApi.getMovieRecommendations(movieId).also { completeImagePaths(it) }.results
            ?: listOf()

    private fun completeImagePaths(movies: MoviesData) =
        movies.results?.forEach { movie -> assignImagePaths(movie) }

    private fun assignImagePaths(movie: MovieBasicData) {
        movie.posterPath = IMAGE_PATH_COMPLETION + movie.posterPath
        movie.backdropPath = IMAGE_PATH_COMPLETION + movie.backdropPath
    }

    private companion object {
        private const val IMAGE_PATH_COMPLETION = "https://image.tmdb.org/t/p/w500"
    }

}
