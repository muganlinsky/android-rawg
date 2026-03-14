package az.mamedali.rawg.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.mamedali.rawg.room.entities.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres")
    suspend fun getAllGenres(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

}