package com.example.udeoflickr.model


import com.google.gson.annotations.SerializedName

data class PhotoX(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("description")
    val description: Description,
    @SerializedName("id")
    val id: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("title")
    val title: Title,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("views")
    val views: Int
){
    constructor(): this(
        Dates("","","",0,0),
        Description(""),
        "",
        Location(0,0,Country("",""),0.0,0.0),
        "",
        "",
        Title(""),
        Urls(List<Url>(0,{Url("","")})),
        0
    )
}