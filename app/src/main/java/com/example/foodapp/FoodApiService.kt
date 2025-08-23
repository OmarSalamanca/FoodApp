package com.example.foodapp

import retrofit2.http.GET

interface FoodApiService {
    @GET("filter.php?a=Mexican")
    suspend fun getFoodMenu(): FoodResponse
}