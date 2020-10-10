package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Notes(
    @SerializedName("note")
    val note: List<Any>
)