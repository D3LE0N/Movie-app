package com.example.imdb.movies.model.repository.network

interface IMoviesService {

    suspend fun getPage(page: Int, quantityPerPage: Int): PaginationService?
}