package com.erictoader.movietrackerbackend.repo

import com.erictoader.movietrackerbackend.entity.Watchlist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WatchlistRepo : JpaRepository<Watchlist, Int> {

    fun findAllByUserId(userId: Int): List<Watchlist>
}