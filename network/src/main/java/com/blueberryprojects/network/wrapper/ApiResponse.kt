package com.blueberryprojects.network.wrapper

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("resultCount") val statusCode: Int,
    @SerializedName("results") val results: T
)