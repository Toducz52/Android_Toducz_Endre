package com.example.roomapp.retrofit

import com.example.roomapp.model.RestaurantsQuery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET("restaurants")
    suspend fun getPostRestaurants(
        @Query("price") price:String,
        @Query("name") name:String,
        @Query("state") state:String,
        @Query("city") city:String,
        @Query("zip") zip: String,
        @Query("country") country: String,
        @Query("page") page:String
    ) : Response<RestaurantsQuery>
}