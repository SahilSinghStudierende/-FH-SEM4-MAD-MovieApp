package com.example.movieapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
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
            onDeleteClicked = { movieId -> onDeleteMovieClicked(movieId) },   // pass functions to adapter
            onEditClicked = { movie -> onEditMovieClicked(movie) }           // pass functions to adapter
        )

        with(binding) {
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

    // This is called when RecyclerView item edit button is clicked
    private fun onEditMovieClicked(movieObj: FavouriteMovieEntity) {
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(requireNotNull(this.activity), R.style.AlertDialogStyle)
        builder.setTitle("Edit Note")

        // Layout for the EditText Input Field
        val layout = LinearLayout(requireNotNull(this.activity))
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER_HORIZONTAL
        layout.setPadding(50, 0, 50, 0)

        // Input Field Customized
        val input = EditText(requireNotNull(this.activity))
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setTextColor(resources.getColor(R.color.white))
        input.setText(movieObj.note)
        input.setSelection(input.text.toString().length)

        // Add it to the Alert
        layout.addView(input)
        builder.setView(layout)

        // Setup update and cancel buttons
        builder.setPositiveButton("Update") { _, _ ->
            movieObj.note = input.text.toString()
            favouriteMovieViewModel.updateFavouriteMovie(movieObj)
            Log.i("WatchListFragment", "I got it! ${movieObj.note}")
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    // This is called when recyclerview item remove button is clicked
    private fun onDeleteMovieClicked(movieId: Long) {
        favouriteMovieViewModel.deleteFavouriteMovie(movieId)
    }
}
