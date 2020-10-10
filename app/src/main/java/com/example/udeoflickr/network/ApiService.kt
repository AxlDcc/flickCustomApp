package com.example.udeoflickr.network

import com.example.udeoflickr.model.Photo
import com.example.udeoflickr.models.Person.Person
import com.example.udeoflickr.models.Person.photo.PublicPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("rest/")
    fun getPhotos(@Query("method") method:String, @Query("api_key") apiKey:String, @Query("gallery_id") galleryId:String, @Query("page")page: Int, @Query("format") format:String, @Query("nojsoncallback")njcb:String ) : Call<Photo>
    @GET("rest/")
    fun getInfoPhoto(@Query("method") method:String, @Query("api_key") apiKey:String, @Query("photo_id") photoId:String, @Query("format") format:String, @Query("nojsoncallback")njcb:String ) : Call<Photo>

    //New Calls
    @GET("rest/")
    fun getUserInfo(@Query("method") method:String, @Query("api_key") apiKey:String, @Query("user_id") photoId:String, @Query("format") format:String, @Query("nojsoncallback")njcb:String ) : Call<Person>

    @GET("rest/")
    fun getPublicPhotos(@Query("method") method:String, @Query("api_key") apiKey:String, @Query("user_id") galleryId:String, @Query("page")page: Int, @Query("format") format:String, @Query("nojsoncallback")njcb:String ) : Call<PublicPhoto>

}
