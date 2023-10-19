package com.erictoader.data

import com.erictoader.data.remote.abstraction.SeriesRemoteProxy
import com.erictoader.data.repo.SeriesRepoImpl
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

class SeriesRepoImplTest {
    private val seriesRemoteProxy: SeriesRemoteProxy = mockk()
    private val translator: Translator = mockk()
    private lateinit var seriesRepo: SeriesRepoImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        seriesRepo = SeriesRepoImpl(seriesRemoteProxy, translator)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getSeriesWatchlistModule returns correct module`() = runBlocking {
        // Arrange
        val mockWatchlist = listOf(WatchlistEntryDomain(movieId = 1, assetType = AssetType.MOVIE_VOD.name))
        val mockTranslation = "Watchlist"
        coEvery { translator.fetchTranslation(any()) } returns mockTranslation
        coEvery { seriesRemoteProxy.getSeriesDetails(any()) } returns mockk(relaxed = true)

        // Act
        val result = seriesRepo.getSeriesWatchlistModule(mockWatchlist)

        // Assert
        assertEquals(mockTranslation, result.header)
        coVerify { translator.fetchTranslation(any()) }
        coVerify { seriesRemoteProxy.getSeriesDetails(any()) }
    }

    @Test
    fun `test getDetailModules throws exception for unsupported module`(): Unit = runBlocking {
        // Arrange
        val mockModuleTypes = listOf(ModuleType.CAST_MODULE, ModuleType.UNKNOWN)

        // Act & Assert
        assertFailsWith<IllegalStateException> {
            seriesRepo.getDetailModules(1, mockModuleTypes)
        }
    }
}
