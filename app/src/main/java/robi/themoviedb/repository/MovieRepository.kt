package robi.themoviedb.repository

import retrofit2.Response
import robi.themoviedb.data.model.MovieResponse
import robi.themoviedb.network.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(private var movie: MovieApi) {
    suspend fun getDiscoverPopular(page: Int): Response<MovieResponse> = movie.discoverPopular(page)
    suspend fun getSearch(q: String, page: Int): Response<MovieResponse> = movie.search(q, page)
}