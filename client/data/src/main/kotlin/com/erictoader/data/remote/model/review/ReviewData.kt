package com.erictoader.data.remote.model.review

import com.erictoader.data.remote.model.ModelMapper
import com.erictoader.domain.feature.details.model.ReviewDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewData(
    @Json(name = "author") val author: String?,
    @Json(name = "author_details") val authorDetails: AuthorDetailsData?,
    @Json(name = "content") val content: String?,
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "updated_at") val updatedAt: String?,
    @Json(name = "url") val url: String?
) : ModelMapper<ReviewDomain> {

    override fun mapToDomainModel() =
        ReviewDomain(
            author = author,
            content = content,
            createdAt = createdAt,
            id = id,
            updatedAt = updatedAt,
            url = url,
        )
}
