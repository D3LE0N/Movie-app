package com.example.imdb.movies.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.databinding.FragmentSeeLaterMoviesBinding
import com.example.imdb.movies.shared.Movie
import com.example.imdb.movies.ui.fragments.adapter.IOnMovieClickListener
import com.example.imdb.movies.ui.fragments.adapter.MovieListAdapter
import com.example.imdb.movies.viewModel.MoviesSeeLaterViewModel
import com.example.imdb.shared.ImdbApplication
import com.example.imdb.shared.getJson
import com.github.zawadz88.materialpopupmenu.popupMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeeLaterMoviesFragment : Fragment() {

    @Inject
    lateinit var viewModel: MoviesSeeLaterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentSeeLaterMoviesBinding>(
            inflater,
            R.layout.fragment_see_later_movies,
            container,
            false
        )

        binding.viewModel = viewModel

        if (viewModel.adapter.itemCount == 0) {
            viewModel.viewModelScope.launch(Dispatchers.IO) {
                viewModel.getSeeLaterMovies()
            }
        }

        val adapter = viewModel.adapter

        addListeners(adapter, viewModel)

        return binding.root
    }

    private fun addListeners(
        adapter: MovieListAdapter,
        viewModel: MoviesSeeLaterViewModel
    ) {
        adapter.addMovieClickListener(object : IOnMovieClickListener {

            override fun movieClicked(movie: Movie, view: View?) {
                val json = movie.getJson()
                val actionMovie =
                    SeeLaterMoviesFragmentDirections.actionSeeLaterMoviesFragmentToMovieDetailsFragment(
                        json
                    )
                findNavController().navigate(actionMovie)
            }
        })

        adapter.addMenuClickListener(object : IOnMovieClickListener {
            override fun movieClicked(movie: Movie, view: View?) {

                val menu = popupMenu {
                    section {
                        item {
                            label = getString(R.string.remove_from_see_later)
                            callback = {

                                viewModel.viewModelScope.launch(Dispatchers.IO) {
                                    viewModel.removeFromToSeeLater(movie)
                                }


                            }
                        }
                    }
                }

                menu.show(requireContext(), view!!)
            }
        })

        adapter.addFavoriteClickListener(object : IOnMovieClickListener {
            override fun movieClicked(movie: Movie, view: View?) {
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.addOrRemoveFromFavorites(movie)
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as ImdbApplication)
            .component
            .inject(this)
    }
}