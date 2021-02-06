package com.example.roomapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.roomapp.model.User

class RegistrationViewModel(application: Application): AndroidViewModel(application) {

    private  val userViewmodel : LocalDataViewModel = LocalDataViewModel(application)

    fun registration(user:User){
        userViewmodel.addUser(user)
    }

}