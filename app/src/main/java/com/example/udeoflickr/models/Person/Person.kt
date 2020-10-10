package com.example.udeoflickr.models.Person


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("person")
    val person: PersonX,
    @SerializedName("stat")
    val stat: String
)