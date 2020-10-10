package com.example.udeoflickr.models.Person


import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("count")
    val count: Count,
    @SerializedName("firstdate")
    val firstdate: Firstdate,
    @SerializedName("firstdatetaken")
    val firstdatetaken: Firstdatetaken
)