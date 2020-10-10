package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Visibility(
    @SerializedName("isfamily")
    val isfamily: Int,
    @SerializedName("isfriend")
    val isfriend: Int,
    @SerializedName("ispublic")
    val ispublic: Int
)