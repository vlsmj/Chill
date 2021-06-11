package com.blueberryprojects.chill.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.blueberryprojects.repository.fragment.HomeFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the movies.
 *
 * @param repository The repository for this ViewModel that handles fetching of data.
 * @param state This will retain the data if the application decided to kill some
 * instance to save memory.
 */
@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    repository: HomeFragmentRepository,
    state: SavedStateHandle
) : ViewModel() {

    /**
     * Instantiate a LiveData variable to be passed on to the repository.
     */
    private val movieName = state.getLiveData(MOVIE_NAME, "star")

    /**
     * Use the repository to fetch the data.
     */
    val movies = movieName.switchMap {
        repository.getMovies(it).asLiveData()
    }

    /**
     * Get the default list of movies with static search.
     */
    fun getDefaultMovies() {
        this.movieName.value = "star"
    }

    /**
     * Search for a movie.
     */
    fun searchMovies(movieName: String) {
        if (movieName.isEmpty()) getDefaultMovies()
        else this.movieName.value = movieName
    }

    companion object {
        private const val MOVIE_NAME = ""
    }
}