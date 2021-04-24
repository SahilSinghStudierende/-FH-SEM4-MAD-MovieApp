package com.example.movieapp.viewModel

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.*
import com.example.movieapp.models.Question
import com.example.movieapp.models.QuestionCatalogue

class QuizViewModel : ViewModel() {
    private val _currentQuiz = MutableLiveData<Question>()
    val currentQuiz: LiveData<Question>
        get() = _currentQuiz

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int>
        get() = _index

    private val _questionCatalogue = MutableLiveData<MutableList<Question>>()
    val questionCatalogue: LiveData<MutableList<Question>>
        get() = _questionCatalogue

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _endGame = MutableLiveData<Boolean>()
    val endGame: LiveData<Boolean>
        get() = _endGame

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private lateinit var timer: CountDownTimer

    companion object {
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 15000L
    }

    init {
        _score.value = 0
        _index.value = 0
        _questionCatalogue.value = QuestionCatalogue().defaultQuestions
        _currentQuiz.value = questionCatalogue.value!![index.value!!]
        _endGame.value = false

        initTimer(COUNTDOWN_TIME)
    }

    private fun initTimer(timeLeft: Long) {
        Log.i("QuizViewModel", "ON INIT TIMER")

        timer = object : CountDownTimer(
            timeLeft,
            ONE_SECOND
        ) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("QuizViewModel", "On Tick ${currentTime.value}")
                _currentTime.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                _endGame.value = true
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun something() {
                Log.i("Something", "I am on stop!")
            }
        }
        timer.start()
    }

    fun stopTimer() {
        timer.cancel()
        Log.i("QuizViewModel", "Stopped Timer!")
    }

    fun resumeTimer() {
        if (currentTime.value == null || currentTime.value!! <= 0L)
            return;
        initTimer(currentTime.value!! * 1000)
        Log.i("QuizViewModel", "Resumed Timer! Time left ${currentTime.value!!}")
    }

    fun incrementScore() {
        _score.value = score.value?.plus(1)
    }

    fun incrementIndex() {
        _index.value = index.value?.plus(1)
    }

    fun endGame() {
        this.stopTimer()
        _endGame.value = true
    }

    fun nextQuestion() {
        _currentQuiz.value = questionCatalogue.value!![index.value!!]
    }
}
