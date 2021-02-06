package com.example.roomapp.retrofit


import retrofit2.Retrofit
import retrofit2.Converter.Factory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://ratpark-api.imok.space")
            .addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}