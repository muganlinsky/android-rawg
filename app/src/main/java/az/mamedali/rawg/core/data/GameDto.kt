package az.mamedali.rawg.core.data

import az.mamedali.rawg.core.domain.Game
import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val results: List<GameDto>
)

@Serializable
data class GameDto(
    val id: Int,
    val slug: String,
    val name: String,
    val backgroundImage: String?,
    val released: String?,
    val rating: Double,
    val metacritic: Int?
) {
    fun toGame(): Game {
        return Game(
            id = id,
            name = name,
            imageUrl = backgroundImage,
            rating = rating
        )
    }
}