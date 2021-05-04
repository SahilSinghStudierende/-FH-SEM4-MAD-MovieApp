package com.example.movieapp.database

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insertFavouriteMovie(movie: FavouriteMovieEntity): Long

    @Update
    fun updateFavouriteMovie(movie: FavouriteMovieEntity)

    @Query("DELETE FROM favourite_movies_table WHERE id = :id")
    fun deleteFavouriteMovie(id: Long)

    @Query("DELETE FROM favourite_movies_table")
    fun clearFavouriteMovies()

    @Query("SELECT * FROM favourite_movies_table ORDER BY id DESC")
    fun getAllFavouriteMovies(): LiveData<List<FavouriteMovieEntity>>
}
