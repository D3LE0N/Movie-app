package com.example.imdb.movies.model.repository.local.dagger

import com.example.imdb.movies.model.repository.local.IMoviesLocalRepository
import com.example.imdb.movies.model.repository.local.MoviesLocalRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MovieLocalRepositoryBindModule {


    @Binds
    @Singleton
    abstract fun provideMoviesLocalRepository(repository: MoviesLocalRepository): IMoviesLocalRepository
}