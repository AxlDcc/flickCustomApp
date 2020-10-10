package com.example.udeoflickr.gallery_list

import com.example.udeoflickr.model.PhotoX
import com.example.udeoflickr.models.Person.photo.Photo
import com.example.udeoflickr.photo_details.PhotoDetailsContract
import com.example.udeoflickr.photo_details.PhotoDetailsModel

class GalleryListPresenter(private var galleryListView: GalleryListContract.View?) : GalleryListContract.Presenter, GalleryListContract.Model.OnFinishedListener, PhotoDetailsContract.Model.OnFinishedListener {
    private val galleryListModel: GalleryListContract.Model
    private val photoDetailsModel: PhotoDetailsContract.Model
    init {
        galleryListModel = GalleryListModel()
        photoDetailsModel = PhotoDetailsModel()
    }

    override fun getMoreData(pageNo: Int,galleryId: String) {
        if(galleryListView != null){
            galleryListView!!.showProgress()
        }
        galleryListModel.getGallery(this,pageNo,galleryId)
    }

    override fun onDestroy() {
        this.galleryListView = null
    }

    override fun onFailure(t: Throwable) {
        galleryListView!!.onResponseFailure(t)
        if (galleryListView != null){
            galleryListView!!.hideProgress()
        }
    }

    override fun onFinished(galleryArrayList: List<Photo>) {
        galleryListView!!.setDataToRecyclerView(galleryArrayList)
        if (galleryListView != null){
            galleryListView!!.hideProgress()
        }
    }

    override fun requestDataFromServer(galleryId: String) {
        if (galleryListView != null){
            galleryListView!!.showProgress()
        }
        galleryListModel.getGallery(this,1,galleryId)
    }

    override fun requestDataPhoto(photoId: String) {
        photoDetailsModel.getPhotoDetails(this,photoId)
    }

    override fun onFinished(photoxx: PhotoX) {
    galleryListView!!.setDataToDetails(photoxx)
        if (galleryListView != null){
            galleryListView!!.hideProgress()
        }
    }
}