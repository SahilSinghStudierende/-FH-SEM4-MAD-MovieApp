package com.example.movieapp.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.database.FavouriteMovieEntity
import com.example.movieapp.repositories.MovieRepository


class MovieFavoritesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val allFavouriteMovies: LiveData<List<FavouriteMovieEntity>> =
        repository.getAllFavouriteMovies()

    fun insertFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity, context: Context) {
        viewModelScope.launch {
            val id = repository.insertFavouriteMovie(favouriteMovieEntity)
            if (id == -1L) {
                Toast.makeText(
                    context,
                    "Movie already added to the favourite!",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }

            Toast.makeText(
                context,
                "Movie got added to the Watchlist!",
                Toast.LENGTH_SHORT
            ).show()

            Log.i(
                "MovieFavouritesVM",
                "Added Favourite Movie ${favouriteMovieEntity.title} with the  ID $id"
            )
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
