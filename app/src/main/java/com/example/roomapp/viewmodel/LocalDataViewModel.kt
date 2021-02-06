package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.localData.LocalDatabase
import com.example.roomapp.model.FavoriteRestaurants
import com.example.roomapp.model.Restaurant
import com.example.roomapp.model.UploadImg
import com.example.roomapp.repository.LocalDataRepository
import com.example.roomapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    val readFavoriteRestaurants: MutableLiveData<List<FavoriteRestaurants>>
    val readUrlImg:MutableLiveData<List<UploadImg>>
    private val repository: LocalDataRepository


    init {
        val userDao = LocalDatabase.getDatabase(
            application
        ).userDao()
        repository = LocalDataRepository(userDao)
        readAllData = repository.readAllData
        readFavoriteRestaurants = repository.favoriteRestaurants
        readUrlImg = repository.localuploadImg

    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

    fun readlocalUrl(email : String, id: String) {
        viewModelScope.launch (Dispatchers.IO ){
            repository.readLocalImg(email, id)
        }


    }

    fun readFavorite(email : String){
        viewModelScope.launch ( Dispatchers.IO ){
            repository.readFavoriteRestaurants(email)
        }
    }


    fun addFavoriteRestaurant(restaurant: FavoriteRestaurants){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavoriteRestaurant(restaurant)
        }
    }

    fun addUrlImg(urlImg: UploadImg){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUrlImg(urlImg)
        }
    }

    fun deleteUrlImg(urlImg: UploadImg){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUrlImg(urlImg)
        }
    }

    fun deleteFavoriteRestaurant(restaurant: FavoriteRestaurants){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteFavoriteRestaurant(restaurant)
        }
    }

}