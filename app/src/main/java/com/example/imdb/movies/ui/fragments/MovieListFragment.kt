package com.example.imdb.movies.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.imdb.R
import com.example.imdb.databinding.FragmentMovieListBinding
import com.example.imdb.movies.viewModel.MoviesViewModel
import com.example.imdb.shared.ImdbApplication
import javax.inject.Inject

class MovieListFragment : Fragment() {

    @Inject
    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = DataBindingUtil.inflate<FragmentMovieListBinding>(
            inflater,
            R.layout.fragment_movie_list,
            container,
            false
        )

        binding.viewModel = viewModel
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as ImdbApplication)
            .component.inject(this)
    }
}