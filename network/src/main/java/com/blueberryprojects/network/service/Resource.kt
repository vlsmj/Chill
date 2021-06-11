package com.blueberryprojects.network.service

/**
 * This is the state of the service when fetching data from the
 * API or Room database.
 */
sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data)
}


















