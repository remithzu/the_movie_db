package robi.themoviedb.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Result(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /fI5RsaM0NSU6TqztRhA2pal5ezv.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 385687
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Fast X
    @SerializedName("overview")
    val overview: String, // Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path. Now, they confront the most lethal opponent they've ever faced: A terrifying threat emerging from the shadows of the past who's fueled by blood revenge, and who is determined to shatter this family and destroy everything—and everyone—that Dom loves, forever.
    @SerializedName("popularity")
    val popularity: Double, // 362.196
    @SerializedName("poster_path")
    val posterPath: String, // /rHfkMVq4BF5AQlqIqwR1XjcWvDl.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2023-05-17
    @SerializedName("title")
    val title: String, // Fast X
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 0
    @SerializedName("vote_count")
    val voteCount: Int // 0
)
