package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentQuizBinding
import com.example.movieapp.models.QuestionCatalogue

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private val questions = QuestionCatalogue().defaultQuestions
    private var score = 0;
    private var index = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false);

        binding.index = index;
        binding.questionsCount = questions.size
        binding.question = questions[index]

        binding.btnNext.setOnClickListener {
            nextQuestion()
        }

        return binding.root
    }

    private fun nextQuestion(){
        // get selected answer
        // check if is correct answer
        // update score
        // check if there are any questions left
        // show next question OR
        // navigate to QuizEndFragment

        // Check for correct answer
        val answerId = binding.answerBox.checkedRadioButtonId;
        val actionButtonView: View = binding.answerBox.findViewById(answerId)
        val actionIndex = binding.answerBox.indexOfChild(actionButtonView);

        if(questions[index].answers.elementAt(actionIndex).isCorrectAnswer)
            score++

        // Check if questions left
        if ((index + 1) > questions.count())
            throw NotImplementedError()


    }
}
