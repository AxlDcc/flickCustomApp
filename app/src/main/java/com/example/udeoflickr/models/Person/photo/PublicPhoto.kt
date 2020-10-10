package com.example.udeoflickr.models.Person.photo


import com.google.gson.annotations.SerializedName

data class PublicPhoto(
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String
)