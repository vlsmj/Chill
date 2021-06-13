package com.blueberryprojects.chill.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blueberryprojects.chill.databinding.ItemMovieBinding
import com.blueberryprojects.common.entities.Movie
import com.blueberryprojects.util.extensions.toHourAndMinute
import com.blueberryprojects.util.listeners.GlideRequestListener
import com.bumptech.glide.Glide

/**
 * The adapter class to be used in a RecyclerView for
 * getting the list of Movies.
 */
class MovieAdapter :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieComparator()) {

    /**
     * On click listener for an item in the list
     *
     * @return Return the selected movie model from the list.
     */
    lateinit var onClickMovie: (movie: Movie) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     * This is the ViewHolder of the adapter that binds the data to
     * the corresponding views.
     */
    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                movie.run {
                    Glide.with(itemView)
                        .load(getPreviewArtworkUrl())
                        .addListener(GlideRequestListener(imgPreview))
                        .into(imgPreview)

                    val trackPrice = "$$trackPrice"

                    txtTitle.text = trackName
                    txtTitle.isSelected = true
                    txtShortDescription.text = shortDescription ?: longDescription
                    txtGenre.text = primaryGenreName
                    txtPrice.text = trackPrice
                    txtTrackTime.text = trackTimeMillis?.toHourAndMinute()

                    txtTrackTime.isVisible = this.trackTimeMillis != null
                    txtPrice.isVisible = this.trackPrice != null
                }

                root.setOnClickListener {
                    onClickMovie(movie)
                }
            }
        }
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it) }
    }

    /**
     * Compare data and contents to prevent the repating of items shown
     * in the list.
     */
    class MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.trackId == newItem.trackId

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }
}