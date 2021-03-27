package com.example.movieapp.util

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val actor: TextView = itemView.findViewById(R.id.actor_list)
    val creator: TextView = itemView.findViewById(R.id.overview_creator_label)
    val seeDetail: Button = itemView.findViewById(R.id.see_detail_button)
}
