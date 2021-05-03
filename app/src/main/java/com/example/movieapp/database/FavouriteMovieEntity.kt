package com.example.movieapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies_table")
data class FavouriteMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0L,
    @ColumnInfo
    val title: String? = "No title",
    @ColumnInfo
    val note: String = "No Notes"
)
