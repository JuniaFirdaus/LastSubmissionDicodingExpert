package com.dicoding.foodsmapp.core.data.source.remote.network

import com.dicoding.foodsmapp.core.data.source.remote.response.CategoryFoodResponse
import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
    suspend fun getList(): CategoryFoodResponse
}
