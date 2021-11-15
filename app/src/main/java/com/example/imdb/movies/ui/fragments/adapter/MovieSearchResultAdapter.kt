package com.example.imdb.movies.ui.fragments.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.BR
import com.example.imdb.R
import com.example.imdb.databinding.MovieSearchItemBinding
import com.example.imdb.movies.shared.Movie

class MovieSearchResultAdapter(movies: List<Movie> = mutableListOf()) :
    RecyclerView.Adapter<MovieSearchResultAdapter.ViewHolder>(), IOnMovieClickListener {

    private val items = movies.toMutableList()
    private var listener: IOnMovieClickListener? = null

    class ViewHolder(val view: MovieSearchItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(value: Movie) {
            view.setVariable(BR.movie, value)
            view.executePendingBindings()
        }

    }

    fun addMovieClickListener(listener: IOnMovieClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<MovieSearchItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_search_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = items[position]
        holder.bind(value)
        holder.view.onClickMovie = this
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun movieClicked(movie: Movie) {
        listener?.movieClicked(movie)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceMovies(movies: List<Movie>) {

        items.clear()
        items.addAll(movies)

        notifyDataSetChanged()
    }
}