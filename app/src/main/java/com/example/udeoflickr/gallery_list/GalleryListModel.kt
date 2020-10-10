package com.example.udeoflickr.gallery_list

import android.util.Log
import com.example.udeoflickr.models.Person.photo.Photo

import com.example.udeoflickr.models.Person.photo.Photos
import com.example.udeoflickr.models.Person.photo.PublicPhoto
import com.example.udeoflickr.network.ApiClient
import com.example.udeoflickr.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class GalleryListModel : GalleryListContract.Model{
    private val TAG = "GalleryListModel"

    override fun getGallery(
        onFinishedListener: GalleryListContract.Model.OnFinishedListener,
        pageNo: Int,
        idGalery: String
    ) {
        val apiClient = ApiClient()
        val userService = apiClient.getClient().create<ApiService>(ApiService::class.java)
        val call = userService.getPublicPhotos("flickr.people.getPublicPhotos",apiClient.API_KEY,idGalery,pageNo,"json","1")
        call.enqueue(object : Callback<PublicPhoto> {
            override fun onResponse(call: Call<PublicPhoto>, response: Response<PublicPhoto>) {
                try {
                    val responsePhoto = response.body()!!
                    if(responsePhoto.stat == "fail"){
                        val photo: List<Photo>
                        photo = ArrayList()
                        onFinishedListener.onFinished(photo)
                    }else{
                        onFinishedListener.onFinished(responsePhoto.photos.photo)
                    }
                }catch (e : IOException){

                }


            }

            override fun onFailure(call: Call<PublicPhoto>, t: Throwable) {

                onFinishedListener.onFailure(t)
            }

        })

    }

}