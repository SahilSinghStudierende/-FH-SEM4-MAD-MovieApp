package com.example.movieapp.models

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var currentQuiz = MutableLiveData<Question>()
    var index = MutableLiveData<Int>()
    var questions = MutableLiveData<MutableList<Question>>()
    var score = MutableLiveData<Int>()
    var time = MutableLiveData<Long>()

    var endGame = MutableLiveData<Boolean>()

    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 15000L
    }

    val timer: CountDownTimer

    init {
        score.value = 0
        index.value = 0
        questions.value = QuestionCatalogue().defaultQuestions
        currentQuiz.value = questions.value!![index.value!!]
        endGame.value = false

        // Timer Setup
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                time.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                endGame.value = true
            }
        }

        timer.start()
    }
}
