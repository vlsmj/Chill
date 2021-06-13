package com.blueberryprojects.util.listeners

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.blueberryprojects.util.R
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * The Glide listener class for setting the image.
 */
class GlideRequestListener(private val imageView: ImageView) : RequestListener<Drawable> {

    /**
     * If the fetching of the image failed, set the default drawable
     * image for broken image.
     */
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        imageView.setImageResource(R.drawable.ic_baseline_broken_image_64)
        return true
    }

    /**
     * If the fetching of image is successful, set the image resource.
     */
    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        imageView.setImageDrawable(resource)
        return true
    }
}