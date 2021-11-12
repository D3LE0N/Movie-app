package com.example.imdb.movies.model.repository.network.dagger

import com.example.imdb.movies.model.repository.network.IMoviesService
import com.example.imdb.movies.model.repository.network.MovieService
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MovieServiceBindModule {

    @Binds
    @Singleton
    abstract fun getMovieService(service: MovieService): IMoviesService
}