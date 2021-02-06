package com.example.roomapp.localData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.model.FavoriteRestaurants
import com.example.roomapp.model.UploadImg
import com.example.roomapp.model.User

@Database(entities = [User::class, UploadImg::class, FavoriteRestaurants::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): LocalDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}