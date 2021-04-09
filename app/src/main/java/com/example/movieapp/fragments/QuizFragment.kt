package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false);

        // Get the viewmodel
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        viewModel.currentQuiz.observe(viewLifecycleOwner, Observer { newQuiz ->
            binding.question = newQuiz
        })
        viewModel.index.observe(viewLifecycleOwner, Observer { newIndex ->
            binding.index = newIndex
        })

        binding.questionsCount = viewModel.questions.value?.size

        binding.btnNext.setOnClickListener {
            nextQuestion()
        }

        return binding.root
    }

    private fun nextQuestion(){

        // Check for correct answer
        val answerId = binding.answerBox.checkedRadioButtonId;
        val actionButtonView: View = binding.answerBox.findViewById(answerId)
        val actionIndex = binding.answerBox.indexOfChild(actionButtonView);

        if(viewModel.questions.value!![viewModel.index.value!!].answers.elementAt(actionIndex).isCorrectAnswer)
            viewModel.score.value = viewModel.score.value?.plus(1)

        binding.answerBox.clearCheck()

        // Check if questions left
        if ((viewModel.index.value!! + 1) > viewModel.questions.value!!.size - 1) {
            view?.findNavController()?.navigate(QuizFragmentDirections.actionQuizFragmentToQuizEndFragment(viewModel.score.value!!, viewModel.questions.value!!.count()))
            return;
        }

        // Show the next Question if any questions left
        viewModel.index.value = viewModel.index.value?.plus(1)
        viewModel.currentQuiz.value = viewModel.questions.value!![viewModel.index.value!!]
    }
}
