package com.blueberryprojects.chill.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.blueberryprojects.chill.databinding.FragmentHomeBinding
import com.blueberryprojects.chill.ui.home.adapter.MovieAdapter
import com.blueberryprojects.common.entities.Movie
import com.blueberryprojects.network.service.Resource
import com.blueberryprojects.util.PrefManager
import com.blueberryprojects.util.extensions.hideActionBar
import com.blueberryprojects.util.extensions.setActionBarTitle
import com.blueberryprojects.util.extensions.toggleKeyboard
import com.blueberryprojects.util.listeners.OnSearchActionListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    /**
     * Inject shared preference for saving last visit time of home screen.
     *
     * @return Returns a reference of shared preference.
     */
    @Inject
    lateinit var prefManager: PrefManager

    private val viewModel: HomeFragmentViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    /**
     * Create an instance of the adapter to be attached to the RecyclerView.
     */
    private val movieAdapter = MovieAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getDefaultMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.run {
            setActionBarTitle("")

            // Display the date and time of last check saved in the shared preference.
            txtLastTimeChecked.text = prefManager.lastTimeChecked

            // Prepare the adapter and layout for the RecyclerView.
            recyclerView.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            // Observe the data changes coming from the ViewModel that will be attached
            // to the RecyclerView.
            viewModel.movies.observe(viewLifecycleOwner) { result ->
                movieAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading
                recyclerView.isVisible = result is Resource.Success && !result.data.isNullOrEmpty()
                txtNoMoviesFound.isVisible =
                    result is Resource.Error || result is Resource.Success && result.data.isNullOrEmpty()
            }

            // Listen to the EditText input change. Data will be passed to the ViewModel
            // to query new set of Movies.
            edtSearchMovie.setOnEditorActionListener(OnSearchActionListener(onSearch = {
                viewModel.searchMovies(it)

                var searchedMovie = "FEATURED MOVIES"

                if (it != "star" && it != "") searchedMovie = "\"$it\""

                txtMovieLabel.text = searchedMovie

                activity?.toggleKeyboard()
            }))
        }

        // Lambda hook to the RecyclerView that gets the selected Movie.
        movieAdapter.onClickMovie = ::onClickMovie

        return binding.root
    }

    /**
     * Redirect to the Movie fragment that displays the details of the Movie.
     */
    private fun onClickMovie(movie: Movie) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationMovie(
                movie
            )
        )
    }

    /**
     * Hide the action bar.
     */
    override fun onResume() {
        super.onResume()
        hideActionBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}