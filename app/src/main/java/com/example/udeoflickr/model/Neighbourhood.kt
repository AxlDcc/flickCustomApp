package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Neighbourhood(
    @SerializedName("_content")
    val content: String,
    @SerializedName("woeid")
    val woeid: Int
)