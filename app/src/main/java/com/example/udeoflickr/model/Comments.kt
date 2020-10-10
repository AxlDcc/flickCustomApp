package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("_content")
    val content: String
)