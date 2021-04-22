package com.example.movieapp.models

import android.os.CountDownTimer
import android.util.Log
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
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 15000L
    }

    lateinit var timer: CountDownTimer

    init {
        score.value = 0
        index.value = 0
        questions.value = QuestionCatalogue().defaultQuestions
        currentQuiz.value = questions.value!![index.value!!]
        endGame.value = false

        initTimer(COUNTDOWN_TIME)
    }

    private fun initTimer(timeLeft: Long) {
        Log.i("QuizViewModel", "ON INIT TIMER")
        // Timer Setup
        timer = object : CountDownTimer(timeLeft, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("QuizViewModel", "On Tick ${time.value}")
                time.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                endGame.value = true
            }
        }
        timer.start()
    }

    fun stopTimer() {
        timer.cancel()
        Log.i("QuizViewModel", "Stopped Timer!")
    }

    fun resumeTimer() {
        if(time.value == null || time.value!! <= 0L)
            return;
        initTimer(time.value!! * 1000)
        Log.i("QuizViewModel", "Resumed Timer! Time left ${time.value!!}")
    }

    fun incrementScore() {
        score.value = score.value?.plus(1)
    }

    fun incrementIndex() {
        index.value = index.value?.plus(1)
    }

    fun endGame() {
        endGame.value = true
    }

    fun nextQuestion() {
        currentQuiz.value = questions.value!![index.value!!]
    }
}
