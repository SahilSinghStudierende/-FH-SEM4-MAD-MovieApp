package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModelFactory
import com.example.movieapp.R
import com.example.movieapp.database.AppDatabase
import com.example.movieapp.database.FavouriteMovieEntity
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.viewModel.MovieFavoritesViewModel


class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )

        // Create instance of Movie Favourite View Model for adding new Movies
        val applicationContext = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(applicationContext).movieDao

        val viewModelFactory =
            MovieFavoritesViewModelFactory(MovieRepository.getInstance(dataSource))
        val favouriteMovieViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieFavoritesViewModel::class.java)


        val movie = DetailFragmentArgs.fromBundle(requireArguments()).movie
        binding.movie = movie

        // Floating Button
        binding.floatButton.setOnClickListener {
                favouriteMovieViewModel.insertFavouriteMovie(
                    FavouriteMovieEntity(
                        movie.title,
                        ""
                    ), applicationContext
                )
        }

        return binding.root
    }
}
