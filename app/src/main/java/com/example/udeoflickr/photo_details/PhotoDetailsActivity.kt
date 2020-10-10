package com.example.udeoflickr.photo_details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.udeoflickr.model.PhotoX
import com.example.udeoflickr.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.w3c.dom.Text
import java.util.ArrayList
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
import com.example.udeoflickr.R


class PhotoDetailsActivity : AppCompatActivity(), PhotoDetailsContract.View {
    private var pbLoadBackdrop : ProgressBar? = null
    private var pbLoadCast: ProgressBar? = null
    private var photoDetailList : MutableList<PhotoX>? = null
    private var tvTitulo : TextView? = null
    private var tvFecha : TextView? = null
    private var tvViews: TextView? = null
    private var tvId : TextView? = null
    private var tvDescripcion : TextView? = null
    private var tvLocation : TextView? = null
    private var tvUrl : TextView? = null
    private var photoDetailsPresenter : PhotoDetailsPresenter? = null
    private var constante : Constants? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.udeoflickr.R.layout.content_movie_details)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val mIntent = intent
        val photoId = mIntent.getStringExtra(constante?.KEY_PHOTO_ID)


        initUI()
        photoDetailsPresenter = PhotoDetailsPresenter(this)
        photoDetailsPresenter!!.requestPhotoData(photoId)

    }

    private fun initUI(){
        tvDescripcion = findViewById(R.id.tv_photo_detail)
        tvFecha = findViewById(R.id.tv_release_date)
        tvViews = findViewById(R.id.tv_movie_ratings)
        tvTitulo = findViewById(R.id.tv_photo_title)
        tvId = findViewById(R.id.tv_id_foto)
        tvLocation = findViewById(R.id.tv_location)
        tvUrl = findViewById(R.id.photo_Url)

    }


    override fun hideProgress() {
       //pbLoadCast!!.visibility = View.GONE
    }

    override fun onResponseFailure(throwable: Throwable) {
        Log.e(TAG, throwable.message)

    }

    override fun setDataToViews(photoxx: PhotoX) {
        if(photoxx != null){
            tvDescripcion!!.text = photoxx.description.content
            tvFecha!!.text = photoxx.dates.taken
            tvViews!!.text = photoxx.views.toString()
            tvTitulo!!.text = photoxx.title.content
            tvId!!.text = photoxx.id
            tvLocation!!.text = if (photoxx.location != null) photoxx.location.country.content else "N/A"
            tvUrl!!.text = photoxx.urls.url[0].content
        }
    }

    override fun showProgress() {
        //pbLoadBackdrop!!.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        photoDetailsPresenter!!.onDestroy()
    }
    companion object{
        private val TAG = "DetailActivity"
    }

    fun urlRequest(view: View) {
        val openLink = Intent(Intent.ACTION_VIEW, Uri.parse(tvUrl!!.text.toString()))
        startActivity(openLink)
    }


}