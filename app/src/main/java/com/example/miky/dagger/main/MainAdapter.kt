package com.example.miky.dagger.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.miky.dagger.R
import com.example.miky.dagger.data.Joke

class MainAdapter(private var context: Context, private var jokeList: List<Joke>) : RecyclerView.Adapter<MainAdapter.JokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.joke_list_item, parent, false)
        return JokeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class JokeViewHolder(itemView: View): ViewHolder(itemView) {

        fun bind(position: Int) {
            var joke = jokeList[position]
            itemView.findViewById<TextView>(R.id.id_text).text = joke.id.toString()
            itemView.findViewById<TextView>(R.id.joke_text).text = joke.joke
        }
    }
}

