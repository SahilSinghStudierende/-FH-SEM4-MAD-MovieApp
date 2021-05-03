package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mad03_fragments_and_navigation.viewmodels.MovieFavoritesViewModelFactory
import com.example.movieapp.R
import com.example.movieapp.adapter.FavoritesListAdapter
import com.example.movieapp.database.AppDatabase
import com.example.movieapp.database.FavouriteMovieEntity
import com.example.movieapp.databinding.FragmentWatchListBinding
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.viewModel.MovieFavoritesViewModel

class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchListBinding
    private lateinit var favouriteMovieViewModel: MovieFavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watch_list, container, false)

        val adapter = FavoritesListAdapter(
            dataSet = listOf(),     // start with empty list
            onDeleteClicked = {movieId -> onDeleteMovieClicked(movieId)},   // pass functions to adapter
            onEditClicked = {movie -> onEditMovieClicked(movie)}           // pass functions to adapter
        )

        with(binding){
            recyclerView.adapter = adapter
        }

        // Create instance of Movie Favourite View Model
        val applicationContext = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(applicationContext).movieDao

        val viewModelFactory =
            MovieFavoritesViewModelFactory(MovieRepository.getInstance(dataSource))
        favouriteMovieViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieFavoritesViewModel::class.java)

        favouriteMovieViewModel.allFavouriteMovies.observe(viewLifecycleOwner, Observer {
            adapter.updateDataSet(it)
        });

        binding.clearBtn.setOnClickListener {
            favouriteMovieViewModel.clearFavourites()
        }

        return binding.root
    }

    // This is called when recyclerview item edit button is clicked
    private fun onEditMovieClicked(movieObj: FavouriteMovieEntity){
        // TODO implement me
    }

    // This is called when recyclerview item remove button is clicked
    private fun onDeleteMovieClicked(movieId: Long){
        favouriteMovieViewModel.deleteFavouriteMovie(movieId)
    }
}
