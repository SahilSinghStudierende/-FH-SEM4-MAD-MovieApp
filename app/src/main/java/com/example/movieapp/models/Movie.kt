package com.example.movieapp.models

import androidx.annotation.DrawableRes
import com.example.movieapp.R
import java.io.Serializable

data class Movie (
    var title: String? = "Title",
    var rating: Float = 0.0f,
    var genre: List<String> = listOf("No Genre"),
    var creator: List<String> = listOf("No Creator"),
    var actor: List<String> = listOf("No Actor"),
    var description: String = "No description",
    @DrawableRes var img: Int = R.drawable.film_klappe
) : Serializable

