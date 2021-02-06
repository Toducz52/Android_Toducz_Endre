package com.example.roomapp.localData

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.model.FavoriteRestaurants
import com.example.roomapp.model.UploadImg
import com.example.roomapp.model.User


@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>

    @Insert
    suspend fun addUrlImg(urlImg:UploadImg)

    @Insert
    suspend fun addFavoriteRestaurant(restaurant:FavoriteRestaurants)

    @Delete
    suspend fun deleteUrlImg(urlImg:UploadImg)

    @Delete
    suspend fun deleteFavoriteRestaurant(restaurant:FavoriteRestaurants)

    @Query("SELECT * FROM uploadImg_table where email = :email_ and restaurantId = :id")
    fun readLocalUrl(email_:String, id: String): List<UploadImg>

    @Query("SELECT * FROM user_favorite where email = :email_")
    fun readFavorite(email_: String): List<FavoriteRestaurants>
}