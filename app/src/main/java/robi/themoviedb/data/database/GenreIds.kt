package robi.themoviedb.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_ids")
data class GenreIds(
    @PrimaryKey(autoGenerate = true) val idKey: Int,
    @ColumnInfo( name = "ids" )
    val ids: Int, // 432
    @ColumnInfo( name = "movie_id" )
    val moviId: Int, // false
)
