package com.example.imdb.movies.model.repository.cache.dagger

import com.example.imdb.movies.model.repository.cache.IMoviesCacheRepository
import com.example.imdb.movies.model.repository.cache.MoviesCacheRepository
import dagger.Binds
import dagger.Module


@Module
abstract class MoviesCacheModule {

    @Binds
    abstract fun getMoviesCacheRepository(cache: MoviesCacheRepository): IMoviesCacheRepository
}