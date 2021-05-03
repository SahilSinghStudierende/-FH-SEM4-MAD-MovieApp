package com.example.movieapp.repositories

import androidx.lifecycle.LiveData
import com.example.movieapp.database.FavouriteMovieEntity
import com.example.movieapp.database.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieRepository(private val movieDao: MovieDao) {

    suspend fun insertFavouriteMovie(favouriteMovie: FavouriteMovieEntity): Long =
        withContext(Dispatchers.IO) {
            return@withContext movieDao.insertFavouriteMovie(favouriteMovie)
        }

    suspend fun updateFavouriteMovie(favouriteMovie: FavouriteMovieEntity) =
        withContext(Dispatchers.IO) {
            movieDao.updateFavouriteMovie(favouriteMovie)
        }

    suspend fun deleteFavouriteMovie(movieId: Long) =
        withContext(Dispatchers.IO) {
            movieDao.deleteFavouriteMovie(movieId)
        }

    suspend fun clearFavouriteMovies() =
        withContext(Dispatchers.IO) {
            movieDao.clearFavouriteMovies()
        }

    suspend fun getAllFavouriteMovies(): LiveData<List<FavouriteMovieEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext movieDao.getAllFavouriteMovies()
        }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}
