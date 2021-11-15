package com.example.imdb.movies.model

import com.example.imdb.movies.model.repository.local.IMoviesLocalRepository
import com.example.imdb.movies.model.repository.network.IMoviesService
import com.example.imdb.movies.model.repository.network.PaginationService
import com.example.imdb.movies.shared.Movie
import javax.inject.Inject

class MoviesModel @Inject constructor(
    private val cache: IMoviesLocalRepository,
    private val local: IMoviesLocalRepository,
    private val network: IMoviesService

) : IMoviesModel {

    private var currentPage: Int = 1
    private var pagination: PaginationService? = null
    private val quantityPerPage = 30

    override suspend fun getNextMoviePage(): List<Movie> {

        var moviePage = cache.getMoviePage(currentPage)
        if (!moviePage.isNullOrEmpty()) {
            currentPage++
            return moviePage
        }


        moviePage = local.getMoviePage(currentPage)
        if (!moviePage.isNullOrEmpty()) {

            cache.saveMovieList(currentPage, moviePage)
            currentPage++
            return moviePage
        }


        pagination?.let {

            if (currentPage > it.totalPages)
                return emptyList()
        }

        val paginationService = network.getPage(currentPage, quantityPerPage) ?: return emptyList()

        val movies = paginationService.results
        cache.saveMovieList(currentPage, movies)
        local.saveMovieList(currentPage, movies)
        currentPage++
        pagination = paginationService
        return movies
    }

    override suspend fun search(query: String): List<Movie> {

        return network.search(query, 1)?.results ?: emptyList()
    }
}