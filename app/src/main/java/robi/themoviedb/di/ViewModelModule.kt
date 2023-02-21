package robi.themoviedb.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import robi.themoviedb.repository.MovieRepository
import robi.themoviedb.ui.home.HomeViewModel
import robi.themoviedb.ui.search.SearchViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    @Singleton
    fun provideHomeViewModel(application: Application, repository: MovieRepository) = HomeViewModel(application,repository)

    @Provides
    @Singleton
    fun provideSearchViewModel(application: Application, repository: MovieRepository) = SearchViewModel(application,repository)
}