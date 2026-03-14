package az.mamedali.rawg.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import az.mamedali.rawg.game_detail.domain.GameDetail

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "background_image") val backgroundImage: String?,
    @ColumnInfo(name = "website") val website: String?,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "ratings_top") val ratingTop: Int,
    @ColumnInfo(name = "rating_count") val ratingCount: Int,
    @ColumnInfo(name = "metacritic") val metacritic: Int?,
    @ColumnInfo(name = "playtime") val playtime: Int,
    @ColumnInfo(name = "screenshots_count") val screenshotsCount: Int,
    @ColumnInfo(name = "platforms") val platforms: List<String>,
    @ColumnInfo(name = "esrb") val esrb: String?
) {
    fun toGameDetail(): GameDetail {
        return GameDetail(
            id = id,
            name = name,
            description = description,
            released = released,
            image = backgroundImage,
            rating = rating,
            metacritic = metacritic,
            playtime = playtime,
            platforms = platforms,
            esrb = esrb
        )
    }
}