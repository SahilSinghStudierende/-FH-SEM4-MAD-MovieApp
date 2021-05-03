package com.example.movieapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.FavouriteMovieEntity
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.launch


class MovieFavoritesViewModel (
    private val repository: MovieRepository
): ViewModel() {

    fun insertFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity) {
        viewModelScope.launch {
            val id = repository.insertFavouriteMovie(favouriteMovieEntity)
        }
    }

    fun updateFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity) {
        viewModelScope.launch {
            repository.updateFavouriteMovie(favouriteMovieEntity)
        }
    }

    fun deleteFavouriteMovie(movieId: Long) {
        viewModelScope.launch {
            repository.deleteFavouriteMovie(movieId)
        }
    }

    fun clearFavourites() {
        viewModelScope.launch {
            repository.clearFavouriteMovies()
        }
    }

    fun getAllFavouriteMovies() {
        viewModelScope.launch {
            repository.getAllFavouriteMovies()
        }
    }
}
