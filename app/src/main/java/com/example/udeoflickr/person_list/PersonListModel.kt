package com.example.udeoflickr.person_list

import android.util.Log
import com.example.udeoflickr.models.Person.*
import com.example.udeoflickr.network.ApiClient
import com.example.udeoflickr.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PersonListModel : PersonListContract.Model {
    private var TAG = "PersonListModel"
    override fun getPersonList(
        onFinishedListener: PersonListContract.Model.OnFinishedListener,
        userId: String
    ) {
        val apiClient = ApiClient()
        val userService = apiClient.getClient().create<ApiService>(ApiService::class.java)
        val call = userService.getUserInfo("flickr.people.getInfo",apiClient.API_KEY,userId,"json","1")
        call.enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                try {
                    var personInfo = response.body()!!
                    if (personInfo.stat == "fail"){
                        var ppl : PersonX
                        ppl = PersonX(Description(""),"", Location(""),Mobileurl(""),
                            Photosurl(""), Profileurl
                        (""), Realname
                        (""), Username
                        ("")
                        )
                        onFinishedListener.onFinished(ppl)

                    }else{
                        onFinishedListener.onFinished(personInfo.person)
                    }
                }catch (e : IOException){

                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Log.e(TAG, t.toString())
                //onFinishedListener.onFailure(t)
            }

        })
    }
}