package az.mamedali.rawg.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GenreEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genresDao(): GenreDao
}