package az.mamedali.rawg.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import az.mamedali.rawg.search.data.GenreDto

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "games_count") val gamesCount: Int,
    @ColumnInfo(name = "image_background") val imageBackground: String?
) {
    fun toGenre(): GenreDto {
        return GenreDto(
            id = id,
            name = name,
            slug = slug,
            gamesCount = gamesCount,
            imageBackground = imageBackground
        )
    }
}