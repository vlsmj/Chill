package com.blueberryprojects.chill.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.blueberryprojects.chill.databinding.FragmentMovieBinding
import com.blueberryprojects.common.entities.Movie
import com.blueberryprojects.util.ChillMediaPlayer
import com.blueberryprojects.util.extensions.launchWebsiteUrl
import com.blueberryprojects.util.extensions.setActionBarTitle
import com.blueberryprojects.util.extensions.showActionBar
import com.blueberryprojects.util.extensions.toHourAndMinute
import com.blueberryprojects.util.listeners.GlideRequestListener
import com.bumptech.glide.Glide

class MovieFragment : Fragment() {

    /**
     * Instantiate the arguments passed from the navigation actions.
     */
    private val args: MovieFragmentArgs by navArgs()

    /**
     * Get the Movie model from the arguments.
     */
    private val movie: Movie get() = args.movie

    /**
     * Prepare the media player class.
     */
    private lateinit var chillMediaPlayer: ChillMediaPlayer

    private var _binding: FragmentMovieBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        binding.run {
            movie.run {
                //Set action bar title to the selected movie.
                setActionBarTitle(trackName ?: "Movie")

                // If no media file is available, show the placeholder image
                // preview instead.
                if (previewUrl == null) {
                    imgPreview.isVisible = previewUrl.isNullOrEmpty()

                    Glide.with(this@MovieFragment)
                        .load(getPreviewArtworkUrl())
                        .addListener(GlideRequestListener(imgPreview))
                        .into(imgPreview)
                } else {
                    chillMediaPlayer = ChillMediaPlayer(
                        context = requireContext(),
                        playerView = playerView,
                        uri = (previewUrl ?: "").toUri()
                    )
                }

                val artistName = "Starring: $artistName"
                val buyButtonLabel = "BUY NOW $$trackPrice"

                txtYearReleased.text = getReleasedYear()
                txtGenre.text = primaryGenreName
                txtTrackTime.text = trackTimeMillis?.toHourAndMinute()
                txtTitle.text = trackName
                txtLongDescription.text = longDescription
                txtArtistName.text = artistName
                btnBuy.text = buyButtonLabel
                btnBuy.isVisible = !collectionViewUrl.isNullOrEmpty()

                txtTrackTime.isVisible = trackTimeMillis != null

                // Launch browser to see the movie details in iTunes website.
                collectionViewUrl?.let { url ->
                    btnBuy.setOnClickListener { launchWebsiteUrl(url) }
                }
            }
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        if (::chillMediaPlayer.isInitialized) chillMediaPlayer.pause()
    }

    /**
     * Show the action bar for this fragment.
     */
    override fun onResume() {
        super.onResume()
        showActionBar()
        if (::chillMediaPlayer.isInitialized) chillMediaPlayer.resume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::chillMediaPlayer.isInitialized) chillMediaPlayer.releasePlayer()
    }
}