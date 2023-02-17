package robi.themoviedb.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import robi.themoviedb.BuildConfig
import robi.themoviedb.network.MovieApi
import robi.themoviedb.repository.MovieRepository
import robi.themoviedb.ui.home.HomeViewModel
import robi.themoviedb.ui.search.SearchViewModel
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun movieApi() : MovieApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        var api: MovieApi? = null
        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        api = retrofit.create(MovieApi::class.java)
        return api
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi) = MovieRepository(api)

    @Provides
    @Singleton
    fun provideHomeViewModel(application: Application, repository: MovieRepository) = HomeViewModel(application,repository)

    @Provides
    @Singleton
    fun provideSearchViewModel(application: Application, repository: MovieRepository) = SearchViewModel(application,repository)
}