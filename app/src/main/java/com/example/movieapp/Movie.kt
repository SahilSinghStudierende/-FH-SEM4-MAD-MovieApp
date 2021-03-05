package com.example.movieapp

data class Movie(val title: String?, val genre: String?, val rating: Float,
                 val creator: List<String>, val actor: List<String>, val description: String?)
