package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentQuizEndBinding
import com.example.movieapp.models.QuizEndViewModel
import com.example.movieapp.models.QuizEndViewModelFactory

class QuizEndFragment : Fragment() {

    private lateinit var binding: FragmentQuizEndBinding
    private lateinit var viewModel: QuizEndViewModel
    private lateinit var viewModelFactory: QuizEndViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)

        viewModelFactory = QuizEndViewModelFactory(QuizEndFragmentArgs.fromBundle(requireArguments()).quizScore, QuizEndFragmentArgs.fromBundle(requireArguments()).quizCount)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(QuizEndViewModel::class.java)

        binding.scorenumber = viewModel.finalScore
        binding.questionsCount = viewModel.questionCount

        return binding.root
    }

}
