package robi.themoviedb.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import robi.themoviedb.BuildConfig
import robi.themoviedb.data.model.MovieResponse

interface MovieApi {
    @GET("/3/discover/movie")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<MovieResponse>

    @GET("/3/discover/movie")
    suspend fun discoverPopular (
        @Query("page") page: Int,
        @Query("sort_by") popular: String = "popularity.desc",
        @Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<MovieResponse>
}