package com.example.movieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    fun insertFavouriteMovie(movie: FavouriteMovieEntity): Long

    @Update
    fun updateFavouriteMovie(movie: FavouriteMovieEntity)

    @Delete
    fun deleteFavouriteMovie(movieId: Long)

    @Query("DELETE FROM favourite_movies_table")
    fun clearFavouriteMovies()

    @Query("SELECT * FROM favourite_movies_table ORDER BY id")
    fun getAllFavouriteMovies(): LiveData<List<FavouriteMovieEntity>>
}
