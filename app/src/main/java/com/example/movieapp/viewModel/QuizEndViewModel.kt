package com.example.movieapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuizEndViewModel(var finalScore: Int, var questionCount: Int): ViewModel() {
}

class QuizEndViewModelFactory(private val finalScore: Int, private val questionCount: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(QuizEndViewModel::class.java))
            return QuizEndViewModel(
                finalScore,
                questionCount
            ) as T

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
