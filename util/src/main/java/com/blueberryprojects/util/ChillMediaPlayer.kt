package com.blueberryprojects.util

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView

/**
 * This is the media player class that will be used to play
 * available media in a Movie.
 */
class ChillMediaPlayer(
    private val context: Context,
    private val playerView: StyledPlayerView,
    private val uri: Uri
) {

    private lateinit var player: SimpleExoPlayer

    /**
     * Instantiate the media player.
     */
    init {
        setupPlayer()
        setMedia()
    }

    /**
     * Setup the media player.
     */
    private fun setupPlayer() {
        player = SimpleExoPlayer
            .Builder(context)
            .build()

        playerView.player = player
        playerView.setShowNextButton(false)
        playerView.setShowPreviousButton(false)
    }

    /**
     * Set the media URI or file to be played.
     */
    private fun setMedia() {
        player.addMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.play()
    }

    /**
     * Pause media if already playing.
     */
    fun pause() {
        if (::player.isInitialized && player.isPlaying) player.pause()
    }

    /**
     * Resume playing media if is not playing.
     */
    fun resume() {
        if (::player.isInitialized && !player.isPlaying) player.play()
    }

    /**
     * Stop and destroy the media player to prevent memory leaks.
     */
    fun releasePlayer() {
        if (::player.isInitialized) {
            player.run {
                stop()
                release()
            }
        }
    }
}