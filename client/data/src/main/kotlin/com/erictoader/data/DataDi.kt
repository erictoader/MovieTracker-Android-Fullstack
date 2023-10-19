package com.erictoader.data

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.erictoader.data.MovieTrackerUtils.BACK_END_USER_API_PATH
import com.erictoader.data.MovieTrackerUtils.BACK_END_WATCHLIST_API_PATH
import com.erictoader.data.MovieTrackerUtils.BASE_URL_MOVIETRACKER
import com.erictoader.data.MovieTrackerUtils.movieTrackerHttpClient
import com.erictoader.data.PreferencesUtils.PREFERENCES_GENERAL
import com.erictoader.data.TMDBUtils.BASE_URL_TMDB
import com.erictoader.data.TMDBUtils.tmdbHttpClient
import com.erictoader.data.cache.abstraction.UserCacheProxy
import com.erictoader.data.cache.concrete.UserCacheProxyImpl
import com.erictoader.data.cache.datastore.abstraction.DataStoreRepo
import com.erictoader.data.cache.datastore.concrete.DataStoreRepoImpl
import com.erictoader.data.remote.abstraction.MovieRemoteProxy
import com.erictoader.data.remote.abstraction.SeriesRemoteProxy
import com.erictoader.data.remote.abstraction.UserRemoteProxy
import com.erictoader.data.remote.abstraction.WatchlistRemoteProxy
import com.erictoader.data.remote.api.MovieTrackerUserApi
import com.erictoader.data.remote.api.MovieTrackerWatchlistApi
import com.erictoader.data.remote.api.TMDBMoviesApi
import com.erictoader.data.remote.api.TMDBSeriesApi
import com.erictoader.data.remote.concrete.MovieRemoteProxyImpl
import com.erictoader.data.remote.concrete.SeriesRemoteProxyImpl
import com.erictoader.data.remote.concrete.UserRemoteProxyImpl
import com.erictoader.data.remote.concrete.WatchlistRemoteProxyImpl
import com.erictoader.data.repo.MovieRepoImpl
import com.erictoader.data.repo.SeriesRepoImpl
import com.erictoader.data.repo.UserRepoImpl
import com.erictoader.data.repo.WatchlistRepoImpl
import com.erictoader.data.translator.LocalTranslator
import com.erictoader.domain.repo.MovieRepo
import com.erictoader.domain.repo.SeriesRepo
import com.erictoader.domain.repo.UserRepo
import com.erictoader.domain.repo.WatchlistRepo
import com.erictoader.domain.translator.Translator
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {

    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<OkHttpClient>(named(tmdbHttpClient)) {
        OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url()
                val url =
                    originalHttpUrl.newBuilder()
                        .addQueryParameter(BuildConfig.TMDB_API_QUERY, BuildConfig.TMDB_API_KEY)
                        .build()
                request.url(url)
                chain.proceed(request.build())
            }
        }
            .build()
    }

    single<OkHttpClient>(named(movieTrackerHttpClient)) {
        OkHttpClient.Builder().build()
    }

    single<TMDBMoviesApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TMDB)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get(named(tmdbHttpClient)))
            .build()
            .create(TMDBMoviesApi::class.java)
    }

    single<TMDBSeriesApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TMDB)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get(named(tmdbHttpClient)))
            .build()
            .create(TMDBSeriesApi::class.java)
    }

    single<MovieTrackerUserApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIETRACKER + BACK_END_USER_API_PATH)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get(named(movieTrackerHttpClient)))
            .build()
            .create(MovieTrackerUserApi::class.java)
    }

    single<MovieTrackerWatchlistApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIETRACKER + BACK_END_WATCHLIST_API_PATH)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get(named(movieTrackerHttpClient)))
            .build()
            .create(MovieTrackerWatchlistApi::class.java)
    }

    single {
        PreferenceDataStoreFactory.create {
            get<Context>().preferencesDataStoreFile(PREFERENCES_GENERAL)
        }
    }

    single<DataStoreRepo> { DataStoreRepoImpl(get()) }

    single<MovieRepo> { MovieRepoImpl(get(), get()) }

    single<SeriesRepo> { SeriesRepoImpl(get(), get()) }

    single<MovieRemoteProxy> { MovieRemoteProxyImpl(get()) }

    single<SeriesRemoteProxy> { SeriesRemoteProxyImpl(get()) }

    single<UserRepo> { UserRepoImpl(get(), get()) }

    single<WatchlistRepo> { WatchlistRepoImpl(get(), get()) }

    single<UserCacheProxy> { UserCacheProxyImpl(get()) }

    single<UserRemoteProxy> { UserRemoteProxyImpl(get()) }

    single<WatchlistRemoteProxy> { WatchlistRemoteProxyImpl(get()) }

    single<Translator> { LocalTranslator() }
}

private object TMDBUtils {
    const val BASE_URL_TMDB = "https://api.themoviedb.org/3/"
    const val tmdbHttpClient = "tmdb"
}

private object MovieTrackerUtils {
    const val BASE_URL_MOVIETRACKER = BuildConfig.BACK_END_MACHINE_ADDRESS
    const val BACK_END_USER_API_PATH = BuildConfig.BACK_END_USER_API_PATH
    const val BACK_END_WATCHLIST_API_PATH = BuildConfig.BACK_END_WATCHLIST_API_PATH
    const val movieTrackerHttpClient = "movietracker"
}

private object PreferencesUtils {
    const val PREFERENCES_GENERAL = "preferences_general"
}
