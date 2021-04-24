package com.example.movieapp.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieOverviewAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.models.Movie

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )


        // For Recycler View:
        val adapter = MovieOverviewAdapter()
        binding.movieList.adapter = adapter

        adapter.data = listOf<Movie>(
            initializeMovieOne(),
            initializeMovieTwo(),
            initializeMovieThree()
        )

        // For Menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.navdrawmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun initializeMovieOne(): Movie {
        return Movie(
            title = "The Queen's Gambit",
            rating = 3.5f,
            genre = listOf("Drama", "Sport"),
            actor = listOf("Sahil", "Thomas", "Baljinder"),
            creator = listOf("Oliver", "Jennifer"),
            description = "The Queen's Gambit follows the life of an orphan chess prodigy, Elizabeth Harmon, during her quest to become the world's " +
                    "greatest chess player while struggling with emotional problems and drug and alcohol dependency. The title of the series refers to a " +
                    "chess opening of the same name. The story begins in the mid-1950s and proceeds into the 1960s. The story begins in Lexington, Kentucky, where a " +
                    "nine-year-old Beth, having lost her mother in a car crash, is taken to an orphanage where she is taught chess by the building's custodian, Mr. Shaibel. " +
                    "As was common during the 1950s, the orphanage dispenses daily tranquilizer pills to the girls,[5][6] which turns into an addiction for Beth. She quickly " +
                    "becomes a strong chess player due to her visualization skills, which are enhanced by the tranquilizers. A few years later, Beth is adopted by Alma Wheatley " +
                    "and her husband from Lexington. As she adjusts to her new home, Beth enters a chess tournament and wins despite having no prior experience in competitive chess. " +
                    "She develops friendships with several people, including former Kentucky State Champion Harry Beltik, United States National Champion Benny Watts, and journalist " +
                    "and fellow player D.L. Townes. As Beth rises to the top of the chess world and reaps the financial benefits of her success, her drug and alcohol dependency becomes worse.",
            img = R.drawable.queensgambit
        )
    }

    private fun initializeMovieTwo(): Movie {
        return Movie(
            title = "Snyder’s Cut: Justice League Long title",
            rating = 4.5f,
            genre = listOf("Action", "Science-Fiction", "Fantasy", "Adventure"),
            actor = listOf("Ben Affleck", "Henry Cavill"),
            creator = listOf("Zack Snyder"),
            description = "Thousands of years ago, Steppenwolf and his legions of Parademons had attempted to take over the Earth using the combined energies of the three Mother Boxes. " +
                    "The attempt was foiled by a unified alliance including the Olympian Gods, Amazons, Atlanteans, humanity, and extraterrestrial beings.[N 3] After Steppenwolf's army" +
                    " was repelled, the Mother Boxes were separated and hidden in different locations. In the present, humanity is still in mourning two years after Superman's death, which " +
                    "triggered the Mother Boxes' reactivation and Steppenwolf's return to Earth. Steppenwolf aims to regain favor with his master Darkseid by gathering the boxes to form " +
                    "\"The Unity\", which will destroy Earth's ecology and terraform it in the image of Steppenwolf's homeworld.",
            img = R.drawable.justiceleague
        )
    }

    private fun initializeMovieThree(): Movie {
        return Movie(
            title = "Avengers: Endgame",
            rating = 5.0f,
            genre = listOf("Action", "Fantasy", "Adventure", "Science-Fiction"),
            actor = listOf("Robert Downey Jr.", "Chris Evans"),
            creator = listOf("Joe Russo", "Anthony Russo"),
            description = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order " +
                    "to reverse Thanos' actions and restore balance to the universe.",
            img = R.drawable.endgame
        )
    }
}
