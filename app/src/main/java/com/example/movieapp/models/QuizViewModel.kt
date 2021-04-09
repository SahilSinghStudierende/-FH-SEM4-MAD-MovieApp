package com.example.movieapp.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var currentQuiz = MutableLiveData<Question>()
    var index = MutableLiveData<Int>()
    var questions = MutableLiveData<MutableList<Question>>()
    var score = MutableLiveData<Int>()

    init {
        score.value = 0
        index.value = 0
        questions.value = QuestionCatalogue().defaultQuestions
        currentQuiz.value = questions.value!![index.value!!]
    }
}
