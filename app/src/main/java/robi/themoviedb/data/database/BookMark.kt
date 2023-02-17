package robi.themoviedb.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookMark(
    @PrimaryKey(autoGenerate = true) val idKey: Int,
    @ColumnInfo( name = "adult" )
    val adult: Boolean, // false
    @ColumnInfo( name = "backdrop_path" )
    val backdropPath: String, // /fI5RsaM0NSU6TqztRhA2pal5ezv.jpg
    @ColumnInfo( name = "id" )
    val id: Int, // 385687
    @ColumnInfo( name = "original_language" )
    val originalLanguage: String, // en
    @ColumnInfo( name = "original_title" )
    val originalTitle: String, // Fast X
    @ColumnInfo( name = "overview" )
    val overview: String, // Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path. Now, they confront the most lethal opponent they've ever faced: A terrifying threat emerging from the shadows of the past who's fueled by blood revenge, and who is determined to shatter this family and destroy everything—and everyone—that Dom loves, forever.
    @ColumnInfo( name = "popularity" )
    val popularity: Double, // 362.196
    @ColumnInfo( name = "poster_path" )
    val posterPath: String, // /rHfkMVq4BF5AQlqIqwR1XjcWvDl.jpg
    @ColumnInfo( name = "release_date" )
    val releaseDate: String, // 2023-05-17
    @ColumnInfo( name = "title" )
    val title: String, // Fast X
    @ColumnInfo( name = "video" )
    val video: Boolean, // false
    @ColumnInfo( name = "vote_average" )
    val voteAverage: Int, // 0
    @ColumnInfo( name = "vote_count" )
    val voteCount: Int // 0
)