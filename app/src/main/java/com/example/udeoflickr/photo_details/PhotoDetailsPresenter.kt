package com.example.udeoflickr.photo_details

import com.example.udeoflickr.model.PhotoX

class PhotoDetailsPresenter(private var photoDetailView : PhotoDetailsContract.View?) : PhotoDetailsContract.Presenter,PhotoDetailsContract.Model.OnFinishedListener  {
    private val photoDetailsModel : PhotoDetailsContract.Model
    init {
        this.photoDetailsModel = PhotoDetailsModel()
    }

    override fun onDestroy() {
        photoDetailView = null
    }

    override fun requestPhotoData(photoId: String) {
        if(photoDetailView != null ){
            photoDetailView!!.showProgress()
        }
        photoDetailsModel.getPhotoDetails(this,photoId)
    }

    override fun onFinished(photoxx: PhotoX) {
        if (photoDetailView!=null){
            photoDetailView!!.hideProgress()
        }
        photoDetailView!!.setDataToViews(photoxx)
    }

    override fun onFailure(t: Throwable) {
        if (photoDetailView != null){
            photoDetailView!!.hideProgress()
        }
        photoDetailView!!.onResponseFailure(t)
    }
}