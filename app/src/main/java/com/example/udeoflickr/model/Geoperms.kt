package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Geoperms(
    @SerializedName("iscontact")
    val iscontact: Int,
    @SerializedName("isfamily")
    val isfamily: Int,
    @SerializedName("isfriend")
    val isfriend: Int,
    @SerializedName("ispublic")
    val ispublic: Int
)