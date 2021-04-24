package com.example.movieapp.fragments

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentQuizBinding
import com.example.movieapp.viewModel.QuizViewModel

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizViewModel
    private lateinit var buzzer: Vibrator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false);
        buzzer = activity?.getSystemService<Vibrator>()!!


        // Get the viewmodel
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        binding.quizViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.endGame.observe(viewLifecycleOwner, Observer { endGameStatus ->
            if (endGameStatus) {
                viewModel.stopTimer()
                view?.findNavController()?.navigate(
                    QuizFragmentDirections.actionQuizFragmentToQuizEndFragment(
                        viewModel.score.value!!,
                        viewModel.questionCatalogue.value!!.count()
                    )
                )
            }
        })

        binding.btnNext.setOnClickListener {
            nextQuestion()
        }

        return binding.root
    }

    private fun nextQuestion() {
        // Check for correct answer
        try {
            val actionIndex = getActionButtonAnswerIndex()
            if (viewModel.currentQuiz.value!!.answers.elementAt(actionIndex).isCorrectAnswer)
                viewModel.incrementScore()
        } catch (e: Resources.NotFoundException) {
            printNothingSelectedError()
            return;
        }

        binding.answerBox.clearCheck()

        // Check if questions left
        if ((viewModel.index.value!! + 1) > viewModel.questionCatalogue.value!!.size - 1) {
            viewModel.endGame()
            return;
        }

        // Show the next Question if any questions left
        viewModel.incrementIndex()
        viewModel.nextQuestion()
    }

    private fun getActionButtonAnswerIndex(): Int {
        val answerId = binding.answerBox.checkedRadioButtonId;
        if (answerId == -1) {
            throw Resources.NotFoundException()
        }
        val actionButtonView: View = binding.answerBox.findViewById(answerId)
        return binding.answerBox.indexOfChild(actionButtonView);
    }

    private fun printNothingSelectedError() {
        Toast.makeText(activity, "Please choose an answer", Toast.LENGTH_SHORT).show()
        // Vibrate
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            buzzer.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 2000), -1))
        else
            buzzer.vibrate(longArrayOf(0, 2000), -1)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopTimer()
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeTimer()
    }
}
