package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.fragments.HomeFragmentDirections
import com.example.movieapp.models.Movie
import com.example.movieapp.util.ViewHolder
import com.example.movieapp.util.setText

class MovieOverviewAdapter : RecyclerView.Adapter<ViewHolder>(){
    var data = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.movieTitle.text = item.title
        // Binding Adapter
        setText(holder.actor, item.actor)
        setText(holder.creator, item.creator)

        holder.seeDetail.setOnClickListener{view: View ->
            view.findNavController().navigate(HomeFragmentDirections.homeToDetailAction(item))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.movie_overview_item, parent, false)

        return ViewHolder(view)
    }
}
