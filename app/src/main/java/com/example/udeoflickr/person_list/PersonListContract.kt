package com.example.udeoflickr.person_list

import com.example.udeoflickr.models.Person.PersonX

interface PersonListContract {
    interface Model{
        interface OnFinishedListener{
            fun onFinished(personX: PersonX)
            fun onFailure(t : Throwable)

        }
        fun getPersonList(onFinishedListener: OnFinishedListener, userId : String)
    }

    interface View{
        fun showProgress()
        fun hideProgress()
        fun setDataToViews(personX: PersonX)
        fun onResponseFailure(throwable : Throwable)
    }

    interface Presenter{
        fun onDestroy()
        fun requestPersonData(userId: String)
    }
}