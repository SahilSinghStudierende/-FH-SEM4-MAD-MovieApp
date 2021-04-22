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
import com.example.movieapp.models.QuizViewModel

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

        viewModel.currentQuiz.observe(viewLifecycleOwner, Observer { newQuiz ->
            binding.question = newQuiz
        })
        viewModel.index.observe(viewLifecycleOwner, Observer { newIndex ->
            binding.index = newIndex
        })
        viewModel.endGame.observe(viewLifecycleOwner, Observer { newEndGame ->
            if (newEndGame) {
                viewModel.timer.cancel()
                view?.findNavController()?.navigate(
                    QuizFragmentDirections.actionQuizFragmentToQuizEndFragment(
                        viewModel.score.value!!,
                        viewModel.questions.value!!.count()
                    )
                )
            }
        })
        viewModel.time.observe(viewLifecycleOwner, Observer { newTime ->
            binding.timer = newTime
        })

        binding.questionsCount = viewModel.questions.value?.size

        binding.btnNext.setOnClickListener {
            nextQuestion()
        }

        return binding.root
    }

    private fun nextQuestion() {
        // Check for correct answer
        try {
            val actionIndex = getActionButtonAnswerIndex()
            if (viewModel.questions.value!![viewModel.index.value!!].answers.elementAt(actionIndex).isCorrectAnswer)
                viewModel.incrementScore()
        } catch (e: Resources.NotFoundException) {
            Toast.makeText(activity, "Please choose an answer", Toast.LENGTH_SHORT).show()
            // Vibrate
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                buzzer.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 2000), -1))
            else
                buzzer.vibrate(longArrayOf(0, 2000), -1)

            return;
        }

        binding.answerBox.clearCheck()

        // Check if questions left
        if ((viewModel.index.value!! + 1) > viewModel.questions.value!!.size - 1) {
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

    override fun onPause() {
        viewModel.stopTimer()
        super.onPause()
    }

    override fun onResume() {
        viewModel.resumeTimer()
        super.onResume()
    }

}
