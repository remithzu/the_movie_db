package robi.themoviedb.data.database

import androidx.room.*

@Dao
interface GenreIdsDao {
    @Query("SELECT * FROM genre_ids WHERE movie_id=:id")
    fun getListGenre(id: Int): List<GenreIds>

    @Insert
    fun insertGenre(vararg genre: GenreIds)

    @Update
    fun updateGenre(vararg genre: GenreIds)

    @Delete
    fun deleteGenre(vararg genre: GenreIds)
}