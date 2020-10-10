package com.example.udeoflickr.photo_details

import android.util.Log
import com.example.udeoflickr.model.Photo
import com.example.udeoflickr.network.ApiClient
import com.example.udeoflickr.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoDetailsModel : PhotoDetailsContract.Model {

    private var TAG = "PhotoDetailsModel";

    override fun getPhotoDetails(
        onFinishedListener: PhotoDetailsContract.Model.OnFinishedListener,
        photoId: String
    ) {
        val apiClient = ApiClient()
        val userService = apiClient.getClient().create<ApiService>(ApiService::class.java)
        val call = userService.getInfoPhoto("flickr.photos.getInfo",apiClient.API_KEY,photoId,"json","1")
        call.enqueue(object : Callback<Photo> {
            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                var infoPhoto = response.body()!!.photo
                Log.d(TAG,"InfoPhoto received: " + infoPhoto.toString())
                onFinishedListener.onFinished(infoPhoto)
            }

            override fun onFailure(call: Call<Photo>, t: Throwable) {
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }

        })

    }

}