package com.example.udeoflickr.gallery_list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.udeoflickr.MainActivity
import com.example.udeoflickr.R
import com.example.udeoflickr.adapter.PhotosAdapter
import com.example.udeoflickr.model.PhotoX
import com.example.udeoflickr.models.Person.photo.Photo
import com.example.udeoflickr.models.Person.photo.Photos
import com.example.udeoflickr.person_list.PersonListActivity
import com.example.udeoflickr.person_list.PersonListModel
import com.example.udeoflickr.person_list.PersonListPresenter
import com.example.udeoflickr.photo_details.PhotoDetailsActivity
import com.example.udeoflickr.photo_details.PhotoDetailsContract
import com.example.udeoflickr.photo_details.PhotoDetailsPresenter
import com.example.udeoflickr.photo_filter.PhotoFilterActivity
import com.example.udeoflickr.utils.AllowPhotoModel
import com.example.udeoflickr.utils.Constants
import com.example.udeoflickr.utils.GridSpacingItemDecoration
import com.example.udeoflickr.utils.GridSpacingItemDecoration.Companion.dpToPx
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.activity_person_list.*
import kotlin.math.log

class GalleryListActivity : AppCompatActivity(), GalleryListContract.View,MoveItemClickListener, ShowEmptyView {
    private var mAuth: FirebaseAuth?= null
    private var galleryListPresenter: GalleryListPresenter? = null
    private var rvGalleryList: RecyclerView? = null
    private var photoDetails: MutableList<PhotoX>? = null
    private var locations = arrayListOf<String>()
    private var galleryAdapter: PhotosAdapter? = null
    private var pbLoading: ProgressBar? = null
    private var fabFilter: FloatingActionButton? = null
    private var tvEmptyView: TextView? = null
    //private var personId: TextView? = null

    private var pageNo = 1

    //Constants for load more
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0
    private var mLayoutManager: GridLayoutManager? = null
    // Constants for filter functionality
    private var location = ""
    private var top = "0"
    private var constante : Constants? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        mAuth = FirebaseAuth.getInstance()

        supportActionBar!!.title = "Fotos de Perfil"
        initUi()
        button_buscar.setOnClickListener{
            clickEvent()
        }
        button_info.setOnClickListener{
            clickEventInfo()
        }
        //Infinite data lol
        setListeners()
        galleryListPresenter = GalleryListPresenter(this)
    }

    private fun initUi(){
        rvGalleryList = findViewById(R.id.rv_gallery_list)
        photoDetails = ArrayList()
        galleryAdapter = PhotosAdapter(this,photoDetails)
        mLayoutManager = GridLayoutManager(this, 2)
        rvGalleryList!!.layoutManager = mLayoutManager
        rvGalleryList!!.addItemDecoration(GridSpacingItemDecoration(2,dpToPx(this,10),true))
        rvGalleryList!!.itemAnimator = DefaultItemAnimator()
        rvGalleryList!!.adapter = galleryAdapter
        pbLoading = findViewById(R.id.pb_loading)
        fabFilter = findViewById(R.id.fab_filter)
        fabFilter!!.setEnabled(false)
        tvEmptyView = findViewById(R.id.tv_empty_view)

    }
    private fun clickEvent(){
        //initUi()
        val personId = pt_userId.text.toString()
        photoDetails!!.clear()
        galleryListPresenter!!.requestDataFromServer(personId)

    }
    private fun clickEventInfo(){
        val personId:String = pt_userId.text.toString()
        val detailIntent = Intent(this,PersonListActivity::class.java)
        detailIntent.putExtra("personId",personId)
        startActivity(detailIntent)
    }

    private fun setListeners(){
        rvGalleryList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = rvGalleryList!!.childCount
                totalItemCount = mLayoutManager!!.itemCount
                firstVisibleItem = mLayoutManager!!.findFirstCompletelyVisibleItemPosition()

                //Scroll
                if (loading){
                    if (totalItemCount> previousTotal){
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if(!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold){
                    //Modificar aqui para cambios
                    //galleryListPresenter!!.getMoreData(pageNo,personId.toString())
                    loading = true
                }

                //hide and show filter button
                if (dy > 0 && fabFilter!!.visibility == View.VISIBLE){
                    fabFilter!!.hide()
                } else if(dy < 0 && fabFilter!!.visibility != View.VISIBLE){
                    fabFilter!!.show()
                }
            }

        })

        fabFilter!!.setOnClickListener {
            // Going to filter screen
            val movieFilterIntent = Intent(this@GalleryListActivity, PhotoFilterActivity::class.java)
            movieFilterIntent.putExtra("LOCATION", location)
            movieFilterIntent.putExtra("TOP", top)
            movieFilterIntent.putExtra("OBJECT",locations)
            startActivityForResult(movieFilterIntent, 999)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 999){
            if (resultCode == Activity.RESULT_OK){

                location = data!!.getStringExtra("LOCATION")
                Log.d("TOP",top)
                top = data!!.getStringExtra("TOP")
                galleryAdapter!!.setFilterLocation(location)
                galleryAdapter!!.setFilterTop(top)
                galleryAdapter!!.filter.filter("")
                galleryAdapter!!.notifyDataSetChanged()

                //Filtros
                getFilteredLocaData(location,top)

                //this.photoDetails = galleryAdapter!!.getFilteredCountry(location).toMutableList()
            }
        }
    }
    private fun getFilteredLocaData(location: String, top : String){
        if (location == "Seleccione un Pais" && top == "0"){
            this.photoDetails = this.photoDetails
        } else if(top == "1") {
            this.photoDetails = galleryAdapter!!.getFilteredRate(top).toMutableList()
        }else{
            this.photoDetails = galleryAdapter!!.getFilteredCountry(location).toMutableList()
        }
    }
    override fun showProgress() {
        pbLoading!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbLoading!!.visibility = View.GONE
    }

    override fun setDataToRecyclerView(galleryArrayList: List<Photo>) {
        if (galleryArrayList.size >= 1){
            fabFilter!!.setEnabled(true)
        }
        newData(galleryArrayList)

        //galleryList!!.addAll(galleryArrayList)
        //galleryAdapter!!.notifyDataSetChanged()
        //pageNo++
    }

    private fun newData(galleryArrayList: List<Photo>){
        for (item in galleryArrayList){
            galleryListPresenter!!.requestDataPhoto(item.id )
        }
    }

    override fun onResponseFailure(throwable: Throwable) {
        Log.e(TAG, throwable.message)
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        galleryListPresenter!!.onDestroy()
    }

    override fun hideEmptyView() {
        rvGalleryList!!.visibility = View.VISIBLE
        tvEmptyView!!.visibility = View.GONE    }

    override fun showEmptyView() {
        rvGalleryList!!.visibility = View.GONE
        tvEmptyView!!.visibility = View.VISIBLE
    }

    override fun onMovieItemClick(position: Int) {
        if (position == -1){
            return
        }
        val detailIntent = Intent(this,PhotoDetailsActivity::class.java)
        detailIntent.putExtra(constante?.KEY_PHOTO_ID, photoDetails!![position].id)
        startActivity(detailIntent)
    }

    companion object{
        private val TAG = "ListActivity"
    }

    override fun setDataToDetails(photoDetail: PhotoX) {
        photoDetails!!.add(photoDetail)
        galleryAdapter!!.notifyDataSetChanged()
        pageNo++

        for(item in photoDetails!!){
            if (item.location != null ){
                locations.add(item.location.country.content)
            }else{
                continue
            }
        }

    }

    fun logoutFirebase(view: View) {
        mAuth!!.signOut()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        LoadUser()
    }
    fun LoadUser(){
        var currentUser = mAuth!!.currentUser
        if (currentUser == null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

}