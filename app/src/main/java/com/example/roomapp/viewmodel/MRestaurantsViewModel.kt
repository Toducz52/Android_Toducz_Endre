package com.example.roomapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapp.MainActivity
import com.example.roomapp.model.Restaurant
import com.example.roomapp.model.RestaurantsQuery
import com.example.roomapp.repository.RestaurantsRepository

import kotlinx.coroutines.launch

class MRestaurantsViewModel(private val repository: RestaurantsRepository) : ViewModel(){

    val myResponseRestaurant: MutableLiveData<MutableList<Restaurant>> = MutableLiveData()
    var list : MutableList<Restaurant> = arrayListOf()
    var pageMutableLiveData: MutableLiveData<Int> = MutableLiveData<Int>()
    var searchParameters = MainActivity.searchParameters
    var get = true


    fun getPostRestaurants(
        price:String,
        name:String,
        state:String,
        city:String,
        zip: String,
        country: String,
        page:String) {


        viewModelScope.launch {
            val response = repository.getPostRestaurants(price, name, state, city, zip, country, page)
            if(response.isSuccessful){
                    Log.d("MRestaurantsViewModel", "list = ${list}")
                    list.addAll(response.body()?.restaurants!!)
                    if(!list.isEmpty()){
                        myResponseRestaurant.value = list
                    }
            }
        }
    }
}