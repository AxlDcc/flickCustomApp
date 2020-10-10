package com.example.udeoflickr.person_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.udeoflickr.R
import com.example.udeoflickr.models.Person.PersonX

class PersonListActivity : AppCompatActivity(), PersonListContract.View {

    //Declarations
    private var tvId : TextView? = null
    private var tvUser : TextView? = null
    private var tvName : TextView? = null
    private var tvLocation : TextView? = null
    private var tvUrl : TextView? = null





    //Data
    private var personListPresenter : PersonListPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        initUI()
        val mIntent = intent
        val personId = mIntent.getStringExtra("personId")

        personListPresenter = PersonListPresenter(this)
        personListPresenter!!.requestPersonData(personId)

    }

    private fun initUI(){
        tvId = findViewById(R.id.tv_person_id)
        tvUser = findViewById(R.id.tv_person_user)
        tvName = findViewById(R.id.tv_person_name)
        tvLocation = findViewById(R.id.tv_person_location)
        tvUrl = findViewById(R.id.tv_person_url)
    }

    override fun setDataToViews(personX: PersonX) {
        if(personX != null){
            tvId!!.text = personX.id
            tvUser!!.text = personX.username.content
            tvName!!.text = personX.realname.content
            tvLocation!!.text = personX.location.content
            tvUrl!!.text = personX.mobileurl.content
        }
    }

    override fun onResponseFailure(throwable: Throwable) {

        Log.e(TAG, throwable.message)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        personListPresenter!!.onDestroy()

    }

    override fun hideProgress() {

    }

    override fun showProgress() {

    }
    companion object{
        private val TAG = "PersonActivity"
    }
}

