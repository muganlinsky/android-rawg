package az.mamedali.rawg.search.data

import az.mamedali.rawg.room.dao.GenreDao
import az.mamedali.rawg.room.entities.GenreEntity

class GenresLocalDataSource(
    private val dao: GenreDao
) {
    suspend fun fetchGenres(): List<GenreEntity> {
        return dao.getAllGenres()
    }

    suspend fun saveGenres(genres: List<GenreEntity>) {
        dao.insertGenres(genres)
    }
}