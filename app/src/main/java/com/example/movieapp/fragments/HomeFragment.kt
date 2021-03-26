package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.models.Movie

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        val bundle = bundleOf("movie" to initializeMovie())

        binding.toDetailButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.home_to_detail_action, bundle)
        }

        return binding.root
    }

    private fun initializeMovie(): Movie {
        return Movie(
            title = "Irgendein Titel",
            rating = 3.5f,
            genre = listOf("Drama", "Sport"),
            actor = listOf("Sahil", "Thomas", "Baljinder"),
            creator = listOf("Oliver", "Jennifer"),
            description = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis " +
                    "et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui " +
                    "ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut " +
                    "labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? " +
                    "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"
        )
    }
}
