package com.example.imdb.movies.model.dagger

import com.example.imdb.movies.model.IMoviesModel
import com.example.imdb.movies.model.MoviesModel
import dagger.Binds
import dagger.Module

@Module
abstract class MoviesModelBindModule {

    @Binds
    abstract fun getMoviesModule(module: MoviesModel): IMoviesModel
}