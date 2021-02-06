package com.example.roomapp.model

data class SearchParameters (
    var price:String,
    var name:String,
    var state:String,
    var city:String,
    var zip:String,
    var country:String,
    var page:String
)