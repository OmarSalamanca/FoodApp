package com.example.foodapp

class FoodRepository {

    suspend fun getFoodMenu(): FoodResponse {

        return RetrofitInstance.api.getFoodMenu()
    }
}