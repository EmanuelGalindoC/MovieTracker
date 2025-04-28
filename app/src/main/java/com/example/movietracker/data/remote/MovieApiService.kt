package com.example.movietracker.data.remote

import com.example.movietracker.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface MovieApiService {
    @GET("movies")
    suspend fun getMovies(
        @Query("search") search: String? = null
    ): Response<List<Movie>>

    @GET("movies/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): Response<Movie>
}