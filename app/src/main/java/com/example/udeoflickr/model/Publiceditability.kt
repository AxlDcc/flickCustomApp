package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Publiceditability(
    @SerializedName("canaddmeta")
    val canaddmeta: Int,
    @SerializedName("cancomment")
    val cancomment: Int
)