package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Urls(
    @SerializedName("url")
    val url: List<Url>
)