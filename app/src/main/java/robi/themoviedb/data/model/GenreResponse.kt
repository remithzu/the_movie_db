package robi.themoviedb.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)