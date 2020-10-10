package com.example.udeoflickr.models.Person


import com.google.gson.annotations.SerializedName

data class Username(
    @SerializedName("_content")
    val content: String
)