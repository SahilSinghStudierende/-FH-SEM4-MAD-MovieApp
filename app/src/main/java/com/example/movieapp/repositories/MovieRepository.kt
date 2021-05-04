package com.example.movieapp.repositories

import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.movieapp.database.FavouriteMovieEntity
import com.example.movieapp.database.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieRepository(private val movieDao: MovieDao) {

    suspend fun insertFavouriteMovie(favouriteMovie: FavouriteMovieEntity): Long =
        withContext(Dispatchers.IO) {
            try {
                return@withContext movieDao.insertFavouriteMovie(favouriteMovie)
            } catch(e: SQLiteException) {
                Log.e("MovieRepository", "Could not Insert ${favouriteMovie.title} - returning ID -1")
                return@withContext -1L
            }
        }

    suspend fun updateFavouriteMovie(favouriteMovie: FavouriteMovieEntity) =
        withContext(Dispatchers.IO) {
            movieDao.updateFavouriteMovie(favouriteMovie)
        }

    suspend fun deleteFavouriteMovie(movie: Long) =
        withContext(Dispatchers.IO) {
            movieDao.deleteFavouriteMovie(movie)
        }

    suspend fun clearFavouriteMovies() =
        withContext(Dispatchers.IO) {
            movieDao.clearFavouriteMovies()
        }

    fun getAllFavouriteMovies() = movieDao.getAllFavouriteMovies()

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                Log.i("MovieRepository", "Creating a new instance of MovieRepository...")
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}
