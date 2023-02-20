package robi.themoviedb.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDetailResponse(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /xDMIl84Qo5Tsu62c9DGWhmPI67A.jpg
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection,
    @SerializedName("budget")
    val budget: Int, // 250000000
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String, // https://wakandaforevertickets.com
    @SerializedName("id")
    val id: Int, // 505642
    @SerializedName("imdb_id")
    val imdbId: String, // tt9114286
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Black Panther: Wakanda Forever
    @SerializedName("overview")
    val overview: String, // Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death.  As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.
    @SerializedName("popularity")
    val popularity: Double, // 4782.887
    @SerializedName("poster_path")
    val posterPath: String, // /sv1xJUazXeYqALzczSZ3O6nkH75.jpg
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String, // 2022-11-09
    @SerializedName("revenue")
    val revenue: Int, // 855099029
    @SerializedName("runtime")
    val runtime: Int, // 162
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String, // Released
    @SerializedName("tagline")
    val tagline: String, // Forever.
    @SerializedName("title")
    val title: String, // Black Panther: Wakanda Forever
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 7.453
    @SerializedName("vote_count")
    val voteCount: Int // 3263
) {
    @Keep
    data class BelongsToCollection(
        @SerializedName("backdrop_path")
        val backdropPath: String, // /yzVxUMYGKjK3GgmVI2BhmbuL9UY.jpg
        @SerializedName("id")
        val id: Int, // 529892
        @SerializedName("name")
        val name: String, // Black Panther Collection
        @SerializedName("poster_path")
        val posterPath: String // /9ZSPIsxI3TZDgfg0Jzk0RZl4INg.jpg
    )

    @Keep
    data class ProductionCompany(
        @SerializedName("id")
        val id: Int, // 420
        @SerializedName("logo_path")
        val logoPath: String, // /hUzeosd33nzE5MCNsZxCGEKTXaQ.png
        @SerializedName("name")
        val name: String, // Marvel Studios
        @SerializedName("origin_country")
        val originCountry: String // US
    )

    @Keep
    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        val iso31661: String, // US
        @SerializedName("name")
        val name: String // United States of America
    )

    @Keep
    data class SpokenLanguage(
        @SerializedName("english_name")
        val englishName: String, // English
        @SerializedName("iso_639_1")
        val iso6391: String, // en
        @SerializedName("name")
        val name: String // English
    )
}