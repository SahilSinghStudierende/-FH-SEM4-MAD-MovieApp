package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.models.Movie
import com.example.movieapp.util.ViewHolder

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
        holder.actor.text = item.actor.toString()
        holder.creator.text = item.creator.toString()


        val bundle = bundleOf("movie" to item)
        holder.seeDetail.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.home_to_detail_action, bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.movie_overview_item, parent, false)

        return ViewHolder(view)
    }
}
