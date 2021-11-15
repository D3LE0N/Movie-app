package com.example.imdb.movies.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.databinding.FragmentMovieListBinding
import com.example.imdb.movies.ui.fragments.adapter.MovieListAdapter
import com.example.imdb.movies.viewModel.MoviesViewModel
import com.example.imdb.shared.ImdbApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    ): View {


        val binding = DataBindingUtil.inflate<FragmentMovieListBinding>(
            inflater,
            R.layout.fragment_movie_list,
            container,
            false
        )
        val adapter = MovieListAdapter()
        viewModel.movieList.observe(viewLifecycleOwner, {
            adapter.addMovies(it)
        })

        binding.viewModel = viewModel
        binding.adapter = adapter

        addPaginationListener(
            binding.movieList,
            binding.movieProgressBar,
            viewModel
        )

        requestPagination(viewModel)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as ImdbApplication)
            .component.inject(this)
    }

    private fun addPaginationListener(
        recyclerView: RecyclerView,
        progressBarFlag: ProgressBar,
        viewModel: MoviesViewModel
    ) {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if (progressBarFlag.isVisible)
                    return

                if (!recyclerView.canScrollVertically(0)) {
                    requestPagination(viewModel)
                }
            }
        })
    }

    private fun requestPagination(viewModel: MoviesViewModel) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.getNextPage()
        }
    }
}