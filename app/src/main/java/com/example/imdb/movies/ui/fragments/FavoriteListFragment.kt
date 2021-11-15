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
import com.example.imdb.databinding.FragmentFavoriteListBinding
import com.example.imdb.movies.shared.Movie
import com.example.imdb.movies.ui.fragments.adapter.IOnMovieClickListener
import com.example.imdb.movies.ui.fragments.adapter.MovieListAdapter
import com.example.imdb.movies.viewModel.MovieFavoriteViewModel
import com.example.imdb.shared.ImdbApplication
import com.example.imdb.shared.getJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteListFragment : Fragment() {

    @Inject
    lateinit var viewModel: MovieFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentFavoriteListBinding>(
            inflater,
            R.layout.fragment_favorite_list,
            container,
            false
        )

        binding.viewModel = viewModel

        if (viewModel.adapter.itemCount == 0) {

            viewModel.viewModelScope.launch(Dispatchers.IO) {

                viewModel.getFavoritesMovies()
            }
        }

        addListeners(viewModel.adapter)

        return binding.root
    }

    private fun addListeners(adapter: MovieListAdapter) {

        adapter.addMenuClickListener(object : IOnMovieClickListener {
            override fun movieClicked(movie: Movie, view: View?) {
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.addOrRemoveFromSeeLater(movie)
                }
            }
        })

        adapter.addFavoriteClickListener(object : IOnMovieClickListener {
            override fun movieClicked(movie: Movie, view: View?) {

                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.removeFromFavorites(movie)
                }
            }
        })

        adapter.addMovieClickListener(object : IOnMovieClickListener {
            override fun movieClicked(movie: Movie, view: View?) {

                val json = movie.getJson()
                val actionMovie =
                    FavoriteListFragmentDirections.actionFavoriteListFragmentToMovieDetailsFragment(
                        json
                    )

                findNavController().navigate(actionMovie)
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