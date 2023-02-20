package robi.themoviedb.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VideoResponse(
    @SerializedName("id")
    val id: Int, // 505642
    @SerializedName("results")
    val results: List<Result>
) {
    @Keep
    data class Result(
        @SerializedName("id")
        val id: String, // 63eaf86e813cb6007941a617
        @SerializedName("iso_3166_1")
        val iso31661: String, // US
        @SerializedName("iso_639_1")
        val iso6391: String, // en
        @SerializedName("key")
        val key: String, // OdDdcf8iWkI
        @SerializedName("name")
        val name: String, // A Conversation with Danai Gurira & Simone Manuel
        @SerializedName("official")
        val official: Boolean, // true
        @SerializedName("published_at")
        val publishedAt: String, // 2023-02-10T21:00:15.000Z
        @SerializedName("site")
        val site: String, // YouTube
        @SerializedName("size")
        val size: Int, // 1080
        @SerializedName("type")
        val type: String // Featurette
    )
}