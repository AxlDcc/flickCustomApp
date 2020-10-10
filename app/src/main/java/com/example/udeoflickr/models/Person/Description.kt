package com.example.udeoflickr.models.Person


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("_content")
    val content: String
)