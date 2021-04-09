package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentQuizEndBinding

class QuizEndFragment : Fragment() {

    private lateinit var binding: FragmentQuizEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)

        binding.scorenumber = QuizEndFragmentArgs.fromBundle(requireArguments()).quizScore
        binding.questionsCount = QuizEndFragmentArgs.fromBundle(requireArguments()).quizCount

        return binding.root
    }

}
