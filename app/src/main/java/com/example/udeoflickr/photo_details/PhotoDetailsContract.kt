package com.example.udeoflickr.photo_details

import com.example.udeoflickr.model.PhotoX

interface PhotoDetailsContract {
    interface Model{
        interface OnFinishedListener{
            fun onFinished(photoxx: PhotoX)
            fun onFailure(t : Throwable)

        }
        fun getPhotoDetails(onFinishedListener: OnFinishedListener, photoId : String)
    }

    interface View{
        fun showProgress()
        fun hideProgress()
        fun setDataToViews(photoxx: PhotoX)
        fun onResponseFailure(throwable : Throwable)
    }

    interface Presenter{
        fun onDestroy()
        fun requestPhotoData(photoId: String)
    }
}