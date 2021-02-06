package com.example.roomapp.repository

import android.text.style.UpdateLayout
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomapp.localData.LocalDao
import com.example.roomapp.model.FavoriteRestaurants
import com.example.roomapp.model.UploadImg
import com.example.roomapp.model.User


class LocalDataRepository(private val localDao: LocalDao) {

    val readAllData: LiveData<List<User>> = localDao.readAllData()
    val favoriteRestaurants: MutableLiveData<List<FavoriteRestaurants>> = MutableLiveData()
    val localuploadImg: MutableLiveData<List<UploadImg>> = MutableLiveData()

    suspend fun addUser(user: User){
        localDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        localDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        localDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        localDao.deleteAllUsers()
    }

    fun readLocalImg(email: String, id: String){
        localuploadImg.postValue(localDao.readLocalUrl(email, id))
    }

    fun readFavoriteRestaurants(email: String){
        var list = localDao.readFavorite(email)
        Log.d("LocalDataRepository", "${list}")
        favoriteRestaurants.postValue(list)
    }

    suspend fun insertFavoriteRestaurant(restaurant:FavoriteRestaurants){
        localDao.addFavoriteRestaurant(restaurant)
    }

    suspend fun insertUrlImg(urlImg:UploadImg){
        localDao.addUrlImg(urlImg)
    }

    suspend fun deleteFavoriteRestaurant(restaurant: FavoriteRestaurants){
        localDao.deleteFavoriteRestaurant(restaurant)
    }

    suspend fun deleteUrlImg(urlImg: UploadImg){
        localDao.deleteUrlImg(urlImg)
    }
}