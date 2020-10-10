package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("accuracy")
    val accuracy: Int,
    @SerializedName("context")
    val context: Int,
    @SerializedName("country")
    val country: Country,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)