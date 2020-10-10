package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("tag")
    val tag: List<Tag>
)