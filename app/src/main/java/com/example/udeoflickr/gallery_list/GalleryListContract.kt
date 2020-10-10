package com.example.udeoflickr.gallery_list

import com.example.udeoflickr.model.PhotoX
import com.example.udeoflickr.models.Person.photo.Photo

interface GalleryListContract {
    //Metodos MVP
    interface Model{
        interface OnFinishedListener{
            fun onFinished(galleryArrayList: List<Photo>)
            fun onFailure(t: Throwable)
        }
        fun getGallery(onFinishedListener: OnFinishedListener,pageNo:Int,idGalery:String)
    }
    interface View{
        fun  showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(galleryArrayList: List<Photo>)
        fun onResponseFailure(throwable: Throwable)
        fun setDataToDetails(photoDetail : PhotoX)
    }

    interface Presenter{
        fun onDestroy()
        fun getMoreData(pageNo: Int,galleryId:String)
        fun requestDataFromServer(galleryId : String)
        fun requestDataPhoto(photoId : String)
    }
}

