package com.blueberryprojects.common.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blueberryprojects.common.interfaces.MediaInterface
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey
    override val trackId: Int,
    override val trackName: String?,
    override val primaryGenreName: String?,
    override val trackTimeMillis: Long?,
    override val releaseDate: String?,
    override val shortDescription: String?,
    override val longDescription: String?,
    override val artworkUrl30: String?,
    override val artworkUrl60: String?,
    override val artworkUrl100: String?,
    override val previewUrl: String?,
    override val artistName: String?,
    override val collectionViewUrl: String?,
    override val trackPrice: Double?
) : MediaInterface, Serializable {

    /**
     * Get the desired preview art work custom size thumbnail.
     *
     * @param width The width of the art work.
     * @param height The height of the art work.
     */
    fun getPreviewArtworkUrl(width: Int? = 640, height: Int? = 640): String? {
        val initialSize = artworkUrl100?.substringAfter("source/") ?: "100x100bb.jpg"

        return artworkUrl100?.replace(initialSize, "${width}x${height}bb.jpg")
    }

    /**
     * Get only the released year from the data. Format the available data to make it so.
     */
    fun getReleasedYear(): String {
        releaseDate?.let { releaseDate ->
            val date: Date? = SimpleDateFormat("yyyy", Locale.getDefault()).parse(releaseDate)
            date?.let {
                val calendar = Calendar.getInstance()
                calendar.time = it

                return calendar.get(Calendar.YEAR).toString()
            }
        }
        return ""
    }
}