package com.example.roomapp.repository

import android.util.Log
import com.example.roomapp.model.RestaurantsQuery
import com.example.roomapp.retrofit.RetrofitInstance
import retrofit2.Response


class RestaurantsRepository {

    var prevResult : Response<RestaurantsQuery>? = null

    suspend fun getPostRestaurants(
        price: String,
        name: String,
        state: String,
        city: String,
        zip: String,
        country: String,
        page: String
    ) : Response<RestaurantsQuery> {

        // ha mar egyszer a vegere ert tobbet ne teretse vissza mert hozza adja az adatokhoz folyamatosan
        // splash screen

        Log.d("RestaurantRepostirory", "page = ${page}")

        /*
        if(page.toInt() > 1) {
            prevResult?.let {
                if (it.body()?.total_entries!! <= (it.body()?.page!! - 1) * it.body()?.per_page!! + it.body()?.restaurants?.size!!) {
                    return retrofit2.Response.error(
                        403,
                        prevResult?.errorBody()
                    )
                }
            }
        }else{
            prevResult = null
        }*/

        var result = RetrofitInstance.api.getPostRestaurants(
            price,
            name,
            state,
            city,
            zip,
            country,
            page
        )
        prevResult = result
        return result
    }
}