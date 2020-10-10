package com.example.udeoflickr.models.Person


import com.google.gson.annotations.SerializedName

data class PersonX(
    @SerializedName("description")
    val description: Description,
    @SerializedName("id")
    val id: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("mobileurl")
    val mobileurl: Mobileurl,
    @SerializedName("photosurl")
    val photosurl: Photosurl,
    @SerializedName("profileurl")
    val profileurl: Profileurl,
    @SerializedName("realname")
    val realname: Realname,
    @SerializedName("username")
    val username: Username
)