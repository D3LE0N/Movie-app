package com.example.imdb.movies.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.imdb.R
import com.example.imdb.databinding.FragmentMovieDetailsBinding
import com.example.imdb.movies.shared.Movie
import com.example.imdb.movies.viewModel.MovieDetailsViewModel
import com.example.imdb.shared.ImdbApplication
import com.example.imdb.shared.getObject
import javax.inject.Inject


class MovieDetailsFragment :
    Fragment() {

    @Inject
    lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentMovieDetailsBinding>(
            inflater,
            R.layout.fragment_movie_details,
            container,
            false
        )

        val args: MovieDetailsFragmentArgs by navArgs()
        val movie = args.movieJson.getObject<Movie>()
        viewModel.movie.value = movie
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as ImdbApplication)
            .component
            .inject(this)
    }
}