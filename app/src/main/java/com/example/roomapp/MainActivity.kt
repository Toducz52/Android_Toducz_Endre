package com.example.roomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.example.roomapp.model.Restaurant
import com.example.roomapp.model.SearchParameters
import com.example.roomapp.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))


    }

    companion object{
        var loggedUser : User? = null
        var restaurantsDetail : Restaurant? = null
        var searchParameters : SearchParameters = SearchParameters("", "", "", "", "", "", "1")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}