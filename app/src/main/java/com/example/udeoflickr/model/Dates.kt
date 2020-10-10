package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName("lastupdate")
    val lastupdate: String,
    @SerializedName("posted")
    val posted: String,
    @SerializedName("taken")
    val taken: String,
    @SerializedName("takengranularity")
    val takengranularity: Int,
    @SerializedName("takenunknown")
    val takenunknown: Int
)