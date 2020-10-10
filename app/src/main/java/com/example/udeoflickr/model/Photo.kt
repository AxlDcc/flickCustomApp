package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("photo")
    val photo: PhotoX,
    @SerializedName("stat")
    val stat: String
)