package com.example.udeoflickr.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.udeoflickr.R
import com.example.udeoflickr.gallery_list.GalleryListActivity
import com.example.udeoflickr.model.PhotoX
import com.example.udeoflickr.models.Person.photo.Photo
import com.example.udeoflickr.network.ApiClient
import kotlinx.android.synthetic.main.movie_card.view.*

class PhotosAdapter(private val galleryListActivity: GalleryListActivity, private var galleryList: List<PhotoX>?): RecyclerView.Adapter<PhotosAdapter.MyViewHolder>(), Filterable{

    private val originalGalleryList : List<PhotoX>?
    private var location: String? = null
    private var top: String? = null

    init {
        this.originalGalleryList = galleryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card, parent,false  )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photo = galleryList!![position]
        holder.tvMovieTitle.text = photo.title.content


        //Cargar Fotos usando Glide
        Glide.with(galleryListActivity)
            .load(ApiClient().IMAGE_BASE_URL + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.pbLoadImage.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.pbLoadImage.visibility  = View.GONE
                    return false
                }
            })
            .apply(RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
            .into(holder.ivMovieThumb)

        holder.itemView.setOnClickListener { galleryListActivity.onMovieItemClick(position) }

    }

    override fun getItemCount(): Int {
        return galleryList!!.size
    }
    //Set Params
    fun setFilterLocation(location : String){
        this.location = location
    }
    fun setFilterTop(top : String){
        this.top = top
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                var filteredResult: List<PhotoX>? = null
                if (location == "Seleccione un Pais" && top == "0"){
                    filteredResult = originalGalleryList
                } else if(top == "1") {
                    filteredResult = getFilteredRate(top)
                }else{
                    filteredResult = getFilteredCountry(location)

                }
                val result = FilterResults()
                result.values = filteredResult
                return result
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                galleryList = filterResults.values as List<PhotoX>
                Log.d("ListaFinal",galleryList.toString())
                this@PhotosAdapter.notifyDataSetChanged()
                if (itemCount == 0){
                    galleryListActivity.showEmptyView()
                }else{
                    galleryListActivity.hideEmptyView()
                }
            }
        }
    }

     fun getFilteredCountry(location: String?): List<PhotoX>{
        var results = ArrayList<PhotoX>()
        for (item in originalGalleryList!!){
            if (item.location.country.content.isEmpty()){
                continue
            }
            if (item.location.country.content == location){
                results.add(item)
            }
        }
        return results
    }
     fun getFilteredRate(rate: String?): List<PhotoX>{
        var results = ArrayList<PhotoX>()
            for (item in originalGalleryList!!){
                if (item.views == null){
                    continue
                }
                results.add(item)
            }
        val sorted = results.sortedByDescending { it.views }
        val sliceList = sorted.slice(0..2)
        return sliceList
    }


    //Inner Class
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvMovieTitle: TextView
        var ivMovieThumb: ImageView
        var pbLoadImage: ProgressBar

        init {
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title)
            ivMovieThumb = itemView.findViewById(R.id.iv_movie_thumb)
            pbLoadImage = itemView.findViewById(R.id.pb_load_image)
        }
    }
}
