package com.erictoader.data.remote.model.credits

import com.erictoader.data.remote.model.ModelMapper
import com.erictoader.domain.feature.details.model.CastDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastData(
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "gender") val gender: Int?,
    @Json(name = "id") val id: Int?,
    @Json(name = "known_for_department") val knownForDepartment: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "original_name") val originalName: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "profile_path") var profilePath: String?,
    @Json(name = "cast_id") val castId: Int?,
    @Json(name = "character") val character: String?,
    @Json(name = "credit_id") val creditId: String?,
    @Json(name = "order") val order: Int?
) : ModelMapper<CastDomain> {

    override fun mapToDomainModel() =
        CastDomain(
            adult = adult,
            gender = gender,
            id = id,
            knownForDepartment = knownForDepartment,
            name = name,
            originalName = originalName,
            popularity = popularity,
            profilePath = profilePath,
            castId = castId,
            character = character,
            creditId = creditId,
            order = order,
        )
}
