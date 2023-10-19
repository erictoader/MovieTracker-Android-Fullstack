package com.erictoader.movietrackerbackend.entity

import com.erictoader.movietrackerbackend.response.model.WatchlistModel
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
@Table(name = "watchlist")
data class Watchlist(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int = 0,
    @Column(name = "user_id")
    val userId: Int = 0,
    @Column(name = "asset_id")
    val assetId: Int = 0,
    @Column(name = "asset_type")
    val assetType: String = "",
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
) : EntityMapper<WatchlistModel> {

    override fun mapToModel() =
        WatchlistModel(
            id = id,
            userId = userId,
            assetId = assetId,
            assetType = assetType,
            createdAt = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(createdAt)
        )
}
