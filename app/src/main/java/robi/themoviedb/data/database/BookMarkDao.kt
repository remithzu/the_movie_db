package robi.themoviedb.data.database

import androidx.room.*

@Dao
interface BookMarkDao {
    @Query("SELECT * FROM bookmark")
    fun getListBookmark(): List<BookMark>

    @Insert
    fun insertBookmark(vararg bookmark: BookMark)

    @Update
    fun updateBookmark(vararg bookmark: BookMark)

    @Delete
    fun deleteBookmark(vararg bookmark: BookMark)
}