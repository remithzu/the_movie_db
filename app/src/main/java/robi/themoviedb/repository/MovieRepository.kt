package robi.themoviedb.repository

import retrofit2.Response
import robi.themoviedb.data.model.*
import robi.themoviedb.network.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(private var movie: MovieApi) {
    suspend fun getDiscoverPopular(page: Int): Response<MovieResponse> = movie.discoverPopular(page)
    suspend fun getSearch(q: String, page: Int): Response<MovieResponse> = movie.search(q, page)
    suspend fun movieDetail(id: Int): Response<MovieDetailResponse> = movie.movieDetail(id)
    suspend fun genreList(): Response<GenreResponse> = movie.genreList()
    suspend fun getActors(id: Int): Response<ActorResponse> = movie.actors(id)
    suspend fun getTrailer(id: Int): Response<VideoResponse> = movie.trailer(id)
}