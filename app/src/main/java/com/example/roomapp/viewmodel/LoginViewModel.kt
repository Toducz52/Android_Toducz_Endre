package com.example.roomapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.roomapp.MainActivity
import com.example.roomapp.model.User


class LoginViewModel(application: Application): AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    private var usersData: List<User>? = null

    fun loadUserData(users: List<User>){
        usersData = users
    }

    fun login(email : String, password:String) : Boolean{

        var user = usersData?.find { it.email == email }
        if( user == null){
            Toast.makeText(getApplication(), "Email is not exist!", Toast.LENGTH_LONG).show()

        }else{
            if(user.password == password){
                MainActivity.loggedUser = user
                return true;
            }else{
                Toast.makeText(getApplication(), "Incorrect password!", Toast.LENGTH_LONG).show()
            }
        }



        return false
    }
}
