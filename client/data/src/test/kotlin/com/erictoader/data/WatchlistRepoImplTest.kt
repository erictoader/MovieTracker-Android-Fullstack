package com.erictoader.data

import com.erictoader.data.cache.abstraction.UserCacheProxy
import com.erictoader.data.remote.abstraction.WatchlistRemoteProxy
import com.erictoader.data.repo.WatchlistRepoImpl
import com.erictoader.domain.feature.auth.model.UserDomain
import com.erictoader.domain.feature.auth.model.WatchlistEntryDomain
import com.erictoader.domain.feature.common.AssetType
import io.mockk.coEvery
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

class WatchlistRepoImplTest {
    private val watchlistRemoteProxy: WatchlistRemoteProxy = mockk()
    private val userCacheProxy: UserCacheProxy = mockk()
    private lateinit var watchlistRepo: WatchlistRepoImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        watchlistRepo = WatchlistRepoImpl(watchlistRemoteProxy, userCacheProxy)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getMovieEntries returns filtered list`() = runBlocking {
        // Arrange
        val mockUserId = 1
        val mockEntries = listOf(
            WatchlistEntryDomain(movieId = 1, assetType = AssetType.MOVIE_VOD.name),
            WatchlistEntryDomain(movieId = 2, assetType = AssetType.SERIES_VOD.name)
        )
        coEvery { userCacheProxy.retrieveCachedUser() } returns UserDomain(id = mockUserId)
        coEvery {
            watchlistRemoteProxy.getEntriesByUserId(mockUserId).map { it.mapToDomainModel() }
        } returns mockEntries

        // Act
        val result = watchlistRepo.getMovieEntries()

        // Assert
        assertEquals(listOf(mockEntries[0]), result)
    }

    // Repeat similar tests for other methods...

    @Test
    fun `test getLoggedInUserId throws exception when user not found`(): Unit = runBlocking {
        // Arrange
        coEvery { userCacheProxy.retrieveCachedUser() } returns null

        // Act & Assert
        assertFailsWith<IllegalStateException> {
            watchlistRepo.getMovieEntries()
        }
    }

}
