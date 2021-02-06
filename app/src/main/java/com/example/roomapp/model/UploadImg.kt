package com.example.roomapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "uploadImg_table", primaryKeys = ["email", "url", "restaurantId"])
data class UploadImg (
    var email : String,
    var url: String,
    var restaurantId : String

)