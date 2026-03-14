package az.mamedali.rawg.game_detail.data

import az.mamedali.rawg.game_detail.domain.GameDetail
import az.mamedali.rawg.room.entities.GameEntity
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailDto(
    val id: Int,
    val slug: String,
    val name: String,
    val descriptionRaw: String?,
    val released: String?,
    val backgroundImage: String?,
    val backgroundImageAdditional: String?,
    val website: String?,
    val rating: Double,
    val ratingTop: Int,
    val ratingsCount: Int,
    val metacritic: Int?,
    val playtime: Int,
    val screenshotsCount: Int,
    val platforms: List<PlatformItemDto> = emptyList(),
    val esrbRating: EsrbRatingDto?
) {
    fun toGameDetail(): GameDetail {
        return GameDetail(
            id = id,
            name = name,
            description = descriptionRaw,
            released = released,
            image = backgroundImage,
            rating = rating,
            metacritic = metacritic,
            playtime = playtime,
            platforms = platforms.map { it.platform.name },
            esrb = esrbRating?.name
        )
    }

    fun toGameDetailEntity(): GameEntity {
        return GameEntity(
            id = id,
            name = name,
            description = descriptionRaw,
            released = released,
            backgroundImage = backgroundImage,
            rating = rating,
            metacritic = metacritic,
            playtime = playtime,
            platforms = platforms.map { it.platform.name },
            esrb = esrbRating?.name,
            slug = slug,
            website = website,
            screenshotsCount = screenshotsCount,
            ratingCount = ratingsCount,
            ratingTop = ratingTop
        )
    }
}

@Serializable
data class PlatformItemDto(
    val platform: PlatformDto
)

@Serializable
data class PlatformDto(
    val id: Int,
    val name: String,
    val slug: String? = null
)

@Serializable
data class EsrbRatingDto(
    val id: Int,
    val slug: String,
    val name: String
)