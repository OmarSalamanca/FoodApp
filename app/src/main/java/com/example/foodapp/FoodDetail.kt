package com.example.foodapp

data class FoodDetail(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strInstructions: String,
    val ingredients: List<String>
)
