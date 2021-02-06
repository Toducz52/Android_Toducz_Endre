package com.example.roomapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_favorite", primaryKeys = ["email", "id"])
data class FavoriteRestaurants(

    val email: String,
    val id : String,
    val name: String
)
