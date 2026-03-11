package az.mamedali.rawg.core.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    object Home : Route()

    @Serializable
    object Search : Route()

    @Serializable
    object Favorites : Route()

    @Serializable
    data class GameDetail(val gameId: Int)

    @Serializable
    data class GamesByGenre(val genreId: Int)
}