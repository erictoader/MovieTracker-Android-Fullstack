package com.erictoader.data

import com.erictoader.data.remote.abstraction.MovieRemoteProxy
import com.erictoader.data.repo.MovieRepoImpl
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.AssetType
import com.erictoader.domain.feature.common.ModuleType
import com.erictoader.domain.translator.Translator
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MovieRepoImplTest {
    private val movieRemoteProxy: MovieRemoteProxy = mockk()
    private val translator: Translator = mockk()
    private lateinit var movieRepo: MovieRepoImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        movieRepo = MovieRepoImpl(movieRemoteProxy, translator)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getMovieWatchlistModule returns correct module`() = runBlocking {
        // Arrange
        val mockWatchlist = listOf(WatchlistEntryDomain(movieId = 1, assetType = AssetType.MOVIE_VOD.name))
        val mockTranslation = "Watchlist"
        coEvery { translator.fetchTranslation(any()) } returns mockTranslation
        coEvery { movieRemoteProxy.getMovieDetails(any()) } returns mockk(relaxed = true)  // adjust according to your data model

        // Act
        val result = movieRepo.getMovieWatchlistModule(mockWatchlist)

        // Assert
        assertEquals(mockTranslation, result.header)
        coVerify { translator.fetchTranslation(any()) }
        coVerify { movieRemoteProxy.getMovieDetails(any()) }
    }

    // Repeat similar tests for other methods...

    @Test
    fun `test getDetailModules throws exception for unsupported module`(): Unit = runBlocking {
        // Arrange
        val mockModuleTypes = listOf(ModuleType.CAST_MODULE, ModuleType.UNKNOWN)

        // Act & Assert
        assertFailsWith<IllegalStateException> {
            movieRepo.getDetailModules(1, mockModuleTypes)
        }
    }
}
