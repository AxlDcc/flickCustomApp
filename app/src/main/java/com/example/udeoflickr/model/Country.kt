package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("_content")
    val content: String,
    @SerializedName("woeid")
    val woeid: String
)