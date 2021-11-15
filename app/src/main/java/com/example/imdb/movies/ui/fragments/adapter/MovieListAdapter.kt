package com.example.imdb.movies.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.databinding.MovieItemBinding
import com.example.imdb.movies.shared.Movie

class MovieListAdapter(movies: List<Movie> = mutableListOf()) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>(), IOnMovieClickListener {

    private val items = movies.toMutableList()
    private var listener: IOnMovieClickListener? = null

    class ViewHolder(val view: MovieItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(value: Movie) {
            view.setVariable(BR.movie, value)
            view.executePendingBindings()
        }
    }

    fun addMovieClickListener(listener: IOnMovieClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
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

    override fun getItemCount(): Int = items.size

    override fun movieClicked(movie: Movie) {

        listener?.movieClicked(movie)
    }

    fun addMovies(movies: List<Movie>?) {

        movies?.let {

            val startIndex = items.size - 1
            items.addAll(movies)
            notifyItemRangeChanged(startIndex, startIndex + movies.size)
        }
    }
}