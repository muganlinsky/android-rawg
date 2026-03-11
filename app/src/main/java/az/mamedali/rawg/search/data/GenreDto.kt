package az.mamedali.rawg.search.data

import az.mamedali.rawg.search.domain.Genre
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val results: List<GenreDto>
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String,
    val slug: String,
    val gamesCount: Int,
    val imageBackground: String?
) {
    fun toGenre(): Genre {
        return Genre(
            id = id,
            name = name,
            imageBackground = imageBackground,
            slug = slug,
            gamesCount = gamesCount
        )
    }
}