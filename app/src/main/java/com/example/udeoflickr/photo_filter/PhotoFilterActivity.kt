package com.example.udeoflickr.photo_filter

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.udeoflickr.R
import kotlinx.android.synthetic.main.activity_photo_filter.*

class PhotoFilterActivity : AppCompatActivity() {
    //private texts

    //private data
    private var location = ""
    private var top = "0"
    private var locations = arrayListOf<String>()
    //Intent
    val globalIntent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_filter)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val mIntent = intent
        location = mIntent.getStringExtra("LOCATION")
        top = mIntent.getStringExtra("TOP")
        locations = mIntent.getStringArrayListExtra("OBJECT")
        locations.add(0,"Seleccione un Pais")
        globalIntent.putExtra("TOP", top)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locations.distinct())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        snipperCountry.adapter = adapter

        snipperCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                location = adapter.getItem(p2)
                globalIntent.putExtra("LOCATION", location)
                top = "0"
                globalIntent.putExtra("TOP", top)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                location = ""
            }
        }
        button_Top.setOnClickListener {
            top = "1"
            globalIntent.putExtra("TOP", top)

        }
        setResult(Activity.RESULT_OK, globalIntent)
    }

    private fun clickEvent(){

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
