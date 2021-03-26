package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.models.Movie


class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false)

        binding.movie = arguments?.getSerializable("movie") as Movie?

        return binding.root
    }
}
