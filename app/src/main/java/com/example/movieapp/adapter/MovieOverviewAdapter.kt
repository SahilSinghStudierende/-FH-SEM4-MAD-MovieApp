package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.fragments.HomeFragmentDirections
import com.example.movieapp.models.Movie
import com.example.movieapp.util.setText

class MovieOverviewAdapter : RecyclerView.Adapter<MovieOverviewAdapter.MovieViewHolder>(){
    var data = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]

        holder.movieTitle.text = item.title
        // Binding Adapter
        setText(holder.actor, item.actor)
        setText(holder.creator, item.creator)

        holder.seeDetail.setOnClickListener{view: View ->
            view.findNavController().navigate(HomeFragmentDirections.homeToDetailAction(item))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.movie_overview_item, parent, false)

        return MovieViewHolder(view)
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val actor: TextView = itemView.findViewById(R.id.actor_list)
        val creator: TextView = itemView.findViewById(R.id.overview_creator_label)
        val seeDetail: Button = itemView.findViewById(R.id.see_detail_button)
    }
}
