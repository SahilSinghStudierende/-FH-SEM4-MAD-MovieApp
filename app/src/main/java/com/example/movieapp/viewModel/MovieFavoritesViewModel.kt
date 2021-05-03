package com.example.movieapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.FavouriteMovieEntity
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.launch


class MovieFavoritesViewModel (
    private val repository: MovieRepository
): ViewModel() {

    val allFavouriteMovies: LiveData<List<FavouriteMovieEntity>> = repository.getAllFavouriteMovies()

    init {

    }

    fun insertFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity) {
        viewModelScope.launch {
            val id = repository.insertFavouriteMovie(favouriteMovieEntity)
            Log.i("MovieFavouritesVM", "Added Favourite Movie ${favouriteMovieEntity.title} with the  ID $id")
        }
    }

    fun updateFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity) {
        viewModelScope.launch {
            repository.updateFavouriteMovie(favouriteMovieEntity)
        }
    }

    fun deleteFavouriteMovie(movie: Long) {
        viewModelScope.launch {
            repository.deleteFavouriteMovie(movie)
        }
    }

    fun clearFavourites() {
        viewModelScope.launch {
            repository.clearFavouriteMovies()
        }
    }
}
