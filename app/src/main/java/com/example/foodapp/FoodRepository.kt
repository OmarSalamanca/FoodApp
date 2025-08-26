package com.example.foodapp

class FoodRepository {

    // Fetch food menu (list of meals)
    suspend fun getFoodMenu(): FoodResponse {
        return RetrofitInstance.api.getFoodMenu()
    }

    // Fetch food detail by ID
    suspend fun getFoodDetail(foodId: String): FoodDetail {
        val response: FoodDetailResponse = RetrofitInstance.api.getFoodDetail(foodId)
        val meal: FoodDetailFromApi = response.meals.first()

        // Collect ingredients dynamically
        val ingredients = mutableListOf<String>()
        for (i in 1..20) {
            val field = FoodDetailFromApi::class.java.getDeclaredField("strIngredient$i")
            field.isAccessible = true
            val value = field.get(meal) as? String
            if (!value.isNullOrBlank()) ingredients.add(value)
        }

        return FoodDetail(
            idMeal = meal.idMeal ?: "",
            strMeal = meal.strMeal ?: "",
            strMealThumb = meal.strMealThumb ?: "",
            strInstructions = meal.strInstructions ?: "",
            ingredients = ingredients
        )
    }
}
