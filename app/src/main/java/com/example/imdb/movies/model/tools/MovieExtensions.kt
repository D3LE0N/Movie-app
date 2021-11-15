package com.example.imdb.movies.model.tools

import com.example.imdb.movies.model.repository.local.database.entities.MovieEntity
import com.example.imdb.movies.shared.Movie

fun MovieEntity.toMovie(): Movie {
    val movie = this

    return Movie(
        movie.id,
        movie.title,
        movie.poster,
        movie.overview,
        movie.releaseDate,
        movie.voteAverage,
        movie.backdrop,
        movie.favorite,
        movie.seeLater
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    val movie = this

    return MovieEntity(
        movie.id,
        movie.title,
        movie.poster,
        movie.overview,
        movie.releaseDate,
        movie.voteAverage,
        movie.backdrop,
        movie.favorite,
        movie.seeLater
    )
}

fun List<Movie>?.toMovieEntityList(): List<MovieEntity> {

    val result = mutableListOf<MovieEntity>()
    val list = this

    list?.let {

        for (item in list) {
            result.add(item.toMovieEntity())
        }
    }

    return result
}

fun List<MovieEntity>?.toMovieList(): List<Movie> {

    val result = mutableListOf<Movie>()
    val list = this

    list?.let {

        for (item in list) {
            result.add(item.toMovie())
        }
    }

    return result
}