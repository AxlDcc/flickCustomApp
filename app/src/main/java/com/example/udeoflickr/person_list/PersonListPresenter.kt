package com.example.udeoflickr.person_list

import android.util.Log
import com.example.udeoflickr.models.Person.PersonX
//
class PersonListPresenter(private var personListView : PersonListContract.View?): PersonListContract.Presenter, PersonListContract.Model.OnFinishedListener {

    private val personListModel : PersonListContract.Model
    init {
        this.personListModel = PersonListModel()
    }

    override fun requestPersonData(userId: String) {
        if(personListView != null ){
            //personListView!!.showProgress()
        }
        personListModel.getPersonList(this,userId)
    }

    override fun onFinished(personX: PersonX) {
        if (personListView!=null){
            personListView!!.hideProgress()
        }
        personListView!!.setDataToViews(personX)
    }

    override fun onDestroy() {
        personListView = null
    }

    override fun onFailure(t: Throwable) {
        if (personListView != null){
            //personListView!!.hideProgress()
        }
        personListView!!.onResponseFailure(t)
    }
}