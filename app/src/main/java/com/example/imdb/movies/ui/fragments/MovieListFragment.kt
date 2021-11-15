package com.example.imdb.movies.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.databinding.FragmentMovieListBinding
import com.example.imdb.movies.shared.Movie
import com.example.imdb.movies.ui.fragments.adapter.IOnMovieClickListener
import com.example.imdb.movies.viewModel.MoviesViewModel
import com.example.imdb.shared.ImdbApplication
import com.example.imdb.shared.getJson
import com.github.zawadz88.materialpopupmenu.popupMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListFragment : Fragment(), SearchView.OnQueryTextListener {

    @Inject
    lateinit var viewModel: MoviesViewModel

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
        configureListeners(viewModel, viewLifecycleOwner, binding)

        binding.viewModel = viewModel
        binding.movieSearchView.setOnQueryTextListener(this)
        addPaginationListener(
            binding.movieList,
            binding.movieProgressBar,
            viewModel
        )

        if (viewModel.adapter.itemCount == 0){
            requestPagination(viewModel)
        }
        return binding.root
    }

    private fun configureListeners(
        viewModel: MoviesViewModel,
        owner: LifecycleOwner,
        binding: FragmentMovieListBinding
    ) {

        val adapter = viewModel.adapter
        adapter.addMovieClickListener(object : IOnMovieClickListener {

            override fun movieClicked(movie: Movie, view: View?) {
                val json = movie.getJson()
                val actionMovie =
                    MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(json)
                findNavController().navigate(actionMovie)
            }
        })

        val searchAdapter = viewModel.searchResultAdapter
        searchAdapter.addMovieClickListener(object : IOnMovieClickListener {

            override fun movieClicked(movie: Movie, view: View?) {
                val json = movie.getJson()
                val actionMovie =
                    MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(json)
                findNavController().navigate(actionMovie)
            }
        })

        viewModel.searchVisible.observe(owner, {
            if (it) {
                binding.movieList.visibility = View.GONE
                binding.movieSearchList.visibility = View.VISIBLE
            } else {
                binding.movieList.visibility = View.VISIBLE
                binding.movieSearchList.visibility = View.GONE
            }
        })

        viewModel.loading.observe(owner, {

            binding.movieProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        adapter.addFavoriteClickListener(object : IOnMovieClickListener {
            override fun movieClicked(movie: Movie, view: View?) {

            }
        })

        adapter.addMenuClickListener(object : IOnMovieClickListener {
            override fun movieClicked(movie: Movie, view: View?) {

                val menu = popupMenu {
                    section {
                        item {
                            label = if (movie.seeLater) getString(R.string.remove_from_see_later)
                            else getString(R.string.add_from_see_later)
                            callback = {

                                if (!movie.seeLater) {
                                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                                        viewModel.addToSeeLater(movie)
                                    }
                                } else {
                                    viewModel.viewModelScope.launch(Dispatchers.IO) {
                                        viewModel.removeFromToSeeLater(movie)
                                    }
                                }
                            }
                        }
                    }
                }

                menu.show(requireContext(), view!!)
            }
        })
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

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {

        if (p0.isNullOrEmpty()) {
            viewModel.searchVisible.value = false
            return false
        }
        viewModel.viewModelScope.launch(Dispatchers.IO) {

            p0.let {
                viewModel.search(it)
            }
        }
        return true
    }
}