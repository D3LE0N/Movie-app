package com.example.imdb.movies.viewModel.dagger

import com.example.imdb.movies.model.IMoviesModel
import com.example.imdb.movies.viewModel.MovieFavoriteViewModel
import com.example.imdb.movies.viewModel.MoviesSeeLaterViewModel
import com.example.imdb.movies.viewModel.MoviesViewModel
import dagger.Module
import dagger.Provides

@Module
class MoviesViewModelModule {

    @Provides
    fun getMoviesViewModel(model: IMoviesModel): MoviesViewModel {

        return MoviesViewModel(model)
    }

    @Provides
    fun getMoviesSeeLaterViewModel(model: IMoviesModel): MoviesSeeLaterViewModel {
        return MoviesSeeLaterViewModel(model)
    }

    @Provides
    fun getFavoriteMoviesViewModel(model: IMoviesModel): MovieFavoriteViewModel{

        return MovieFavoriteViewModel(model)
    }
}