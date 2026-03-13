package az.mamedali.rawg.search.data

import az.mamedali.rawg.room.GenreDao
import az.mamedali.rawg.room.GenreEntity

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