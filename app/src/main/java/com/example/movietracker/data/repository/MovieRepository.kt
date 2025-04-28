package com.example.movietracker.data.repository

import com.example.movietracker.data.local.MovieDao
import com.example.movietracker.data.remote.MovieApiService
import com.example.movietracker.data.remote.RetrofitInstance
import com.example.movietracker.model.Movie
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) {
    suspend fun searchMovies(query: String?): Response<List<Movie>> {
        return RetrofitInstance.api.getMovies(query)
    }

    suspend fun getMovieDetails(id: Int): Response<Movie> {
        return RetrofitInstance.api.getMovieDetails(id)
    }
    suspend fun saveFavorite(movie: Movie) {
        movieDao.insert(movie)
    }

    suspend fun saveWatched(movie: Movie) {
        movieDao.update(movie)
    }
}