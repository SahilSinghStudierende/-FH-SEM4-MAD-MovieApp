package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentQuizBinding
import com.example.movieapp.models.QuestionCatalogue
import com.example.movieapp.models.QuizViewModel

class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private val questions = QuestionCatalogue().defaultQuestions
    private var score = 0;

    private lateinit var model: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false);

        // Get the viewmodel
        model = ViewModelProviders.of(this).get(QuizViewModel::class.java)

        model.currentQuiz.observe(viewLifecycleOwner, Observer { newQuiz ->
            binding.question = newQuiz
        })
        model.index.observe(viewLifecycleOwner, Observer { newIndex ->
            binding.index = newIndex
        })

        model.index.value = 0
        model.currentQuiz.value = questions[model.index.value!!]
        binding.questionsCount = questions.size

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

        if(questions[model.index.value!!].answers.elementAt(actionIndex).isCorrectAnswer)
            score++

        binding.answerBox.clearCheck()

        // Check if questions left
        if ((model.index.value!! + 1) > questions.size - 1) {
            view?.findNavController()?.navigate(QuizFragmentDirections.actionQuizFragmentToQuizEndFragment(score, questions.count()))
            return;
        }

        model.index.value = model.index.value?.plus(1)
        model.currentQuiz.value = questions[model.index.value!!]
    }
}
