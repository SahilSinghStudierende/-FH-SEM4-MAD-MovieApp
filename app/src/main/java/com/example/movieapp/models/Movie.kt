package com.example.movieapp.models

data class Movie(
    var title: String,
    var rating: Float,
    var genre: List<String>,
    var creator: List<String>,
    var actor: List<String>,
    var description: String
)
