package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Usage(
    @SerializedName("canblog")
    val canblog: Int,
    @SerializedName("candownload")
    val candownload: Int,
    @SerializedName("canprint")
    val canprint: Int,
    @SerializedName("canshare")
    val canshare: Int
)