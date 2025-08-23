package com.example.foodapp

class FoodRepository {

    suspend fun getFoodMenu() = RetrofitInstance.api.getFoodMenu()
}