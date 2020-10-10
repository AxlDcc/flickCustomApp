package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("haspeople")
    val haspeople: Int
)