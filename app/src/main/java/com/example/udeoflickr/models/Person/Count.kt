package com.example.udeoflickr.models.Person


import com.google.gson.annotations.SerializedName

data class Count(
    @SerializedName("_content")
    val content: String
)