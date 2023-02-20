package robi.themoviedb.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import robi.themoviedb.BuildConfig
import robi.themoviedb.data.model.*

interface MovieApi {
    @GET("/3/discover/movie")
    suspend fun discoverPopular (
        @Query("page") page: Int,
        @Query("sort_by") popular: String = "popularity.desc",
        @Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<MovieResponse>
    @GET("/3/search/movie")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<MovieResponse>

    @GET("/3/movie/{id}")
    suspend fun movieDetail (
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<MovieDetailResponse>

    @GET("/3/genre/movie/list")
    suspend fun genreList (
        @Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<GenreResponse>

    @GET("/3/movie/{id}/credits")
    suspend fun actors (
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<ActorResponse>

    @GET("/3/movie/{id}/videos")
    suspend fun trailer (
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.KEY
    ): Response<VideoResponse>
}