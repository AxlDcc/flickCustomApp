package com.example.udeoflickr.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

  class ApiClient {
    val BASE_URL = "https://api.flickr.com/services/"
    val API_KEY = "52092112dc61244b37c66abf8fba4bc2"
    val IMAGE_BASE_URL = "https://live.staticflickr.com/"

     fun getClient():Retrofit{
         val retrofit:Retrofit =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
         return retrofit
    }
}