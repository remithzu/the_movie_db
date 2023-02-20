package robi.themoviedb.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ActorResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int // 505642
) {
    @Keep
    data class Cast(
        @SerializedName("adult")
        val adult: Boolean, // false
        @SerializedName("cast_id")
        val castId: Int, // 4
        @SerializedName("character")
        val character: String, // Shuri
        @SerializedName("credit_id")
        val creditId: String, // 5a95b93292514154f7004c22
        @SerializedName("gender")
        val gender: Int, // 1
        @SerializedName("id")
        val id: Int, // 1083010
        @SerializedName("known_for_department")
        val knownForDepartment: String, // Acting
        @SerializedName("name")
        val name: String, // Letitia Wright
        @SerializedName("order")
        val order: Int, // 0
        @SerializedName("original_name")
        val originalName: String, // Letitia Wright
        @SerializedName("popularity")
        val popularity: Double, // 40.502
        @SerializedName("profile_path")
        val profilePath: String // /4jdsjY5jKwoNpCMd5nnJFsDmieY.jpg
    )

    @Keep
    data class Crew(
        @SerializedName("adult")
        val adult: Boolean, // false
        @SerializedName("credit_id")
        val creditId: String, // 60326570befb09003e8ff17d
        @SerializedName("department")
        val department: String, // Production
        @SerializedName("gender")
        val gender: Int, // 1
        @SerializedName("id")
        val id: Int, // 7232
        @SerializedName("job")
        val job: String, // Casting
        @SerializedName("known_for_department")
        val knownForDepartment: String, // Production
        @SerializedName("name")
        val name: String, // Sarah Halley Finn
        @SerializedName("original_name")
        val originalName: String, // Sarah Halley Finn
        @SerializedName("popularity")
        val popularity: Double, // 9.032
        @SerializedName("profile_path")
        val profilePath: String // /pI3OhmnHhXLEwuv0Vq6qJHivCJA.jpg
    )
}