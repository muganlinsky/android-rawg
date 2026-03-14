package az.mamedali.rawg.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import az.mamedali.rawg.room.dao.GameDao
import az.mamedali.rawg.room.dao.GenreDao
import az.mamedali.rawg.room.entities.GameEntity
import az.mamedali.rawg.room.entities.GenreEntity

@Database(
    entities = [
        GenreEntity::class,
        GameEntity::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genresDao(): GenreDao
    abstract fun gamesDao(): GameDao
}