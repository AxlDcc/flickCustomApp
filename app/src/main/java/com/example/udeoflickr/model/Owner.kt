package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("iconfarm")
    val iconfarm: Int,
    @SerializedName("iconserver")
    val iconserver: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("nsid")
    val nsid: String,
    @SerializedName("path_alias")
    val pathAlias: String,
    @SerializedName("realname")
    val realname: String,
    @SerializedName("username")
    val username: String
)