package com.example.movieapp.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var currentQuiz = MutableLiveData<Question>()
    var index = MutableLiveData<Int>()

    init {
        currentQuiz.value = Question("No Question", listOf(Answer("No Answer", false)))
        index.value = 0
    }
}
