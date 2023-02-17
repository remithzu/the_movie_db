package robi.themoviedb.data.model
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep


@Keep
data class MovieResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int, // 91
    @SerializedName("total_results")
    val totalResults: Int // 1807
)