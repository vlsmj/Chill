package com.blueberryprojects.common.interfaces

/**
 * Media interface from all kinds of media that can be fetched
 * from the iTunes public API.
 */
interface MediaInterface {

    val trackId: Int
    val trackName: String?
    val primaryGenreName: String?
    val trackTimeMillis: Long?
    val releaseDate: String?
    val shortDescription: String?
    val longDescription: String?
    val artworkUrl30: String?
    val artworkUrl60: String?
    val artworkUrl100: String?
    val previewUrl: String?
    val artistName: String?
    val collectionViewUrl: String?
    val trackPrice: Double?
}