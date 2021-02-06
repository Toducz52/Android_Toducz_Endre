package com.example.roomapp.model

data class RestaurantsQuery(
    val total_entries : Int?,
    val page : Int?,
    val per_page : Int?,
    val restaurants : List<Restaurant>?
)