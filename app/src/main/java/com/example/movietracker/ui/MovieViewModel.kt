package com.example.movietracker.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.data.repository.MovieRepository
import com.example.movietracker.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> get() = _movieDetails

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                val response: Response<List<Movie>> = repository.searchMovies(query)
                if (response.isSuccessful) {
                    _movies.value = response.body()
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            try {
                val response: Response<Movie> = repository.getMovieDetails(id)
                if (response.isSuccessful) {
                    _movieDetails.value = response.body()
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }
    fun saveFavorite(movie: Movie) {
        viewModelScope.launch {
            repository.saveFavorite(movie)
        }
    }
    fun saveWatched(movie: Movie) {
        viewModelScope.launch {
            repository.saveWatched(movie)
        }
    }
}
