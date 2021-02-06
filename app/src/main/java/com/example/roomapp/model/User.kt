package com.example.roomapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey var email: String,
    val name: String,
    val password: String,
    val imgUrl: String,
    val address: String,
    val phoneNumber: String
)