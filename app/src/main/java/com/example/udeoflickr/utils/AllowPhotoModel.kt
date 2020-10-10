package com.example.udeoflickr.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.udeoflickr.model.Photo
import com.example.udeoflickr.model.PhotoX
import com.example.udeoflickr.network.ApiClient
import com.example.udeoflickr.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllowPhotoModel {

    fun getPhotoModel(photoId: String) : PhotoX{
        var data : PhotoX = PhotoX()
        val apiClient = ApiClient()
        val userService = apiClient.getClient().create<ApiService>(ApiService::class.java)
        val call = userService.getInfoPhoto("flickr.photos.getInfo",apiClient.API_KEY,photoId,"json","1")
        call.enqueue(object : Callback<Photo> {
            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                if (response.isSuccessful){
                    data = response.body()!!.photo

                }
            }
            override fun onFailure(call: Call<Photo>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return data
    }
}