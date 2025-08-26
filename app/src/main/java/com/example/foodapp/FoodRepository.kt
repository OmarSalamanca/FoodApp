package com.example.foodapp

class FoodRepository {
    suspend fun getFoodMenu(): FoodResponse {
        return RetrofitInstance.api.getFoodMenu()
    }

    suspend fun getFoodDetail(foodId: String): FoodDetail {

        val response: FoodDetailResponse = RetrofitInstance.api.getFoodDetail(foodId)

        val mealJson = response.meals.first()

        val ingredients = mutableListOf<String>()
        var index = 1
        while (true) {
            val value = mealJson::class.java.getDeclaredField("strIngredient$index")
                .get(mealJson) as? String
            if (value.isNullOrBlank()) break
            ingredients.add(value)
            index++
        }

        return FoodDetail(
            strInstructions = mealJson.strInstructions,
            ingredients = ingredients
        )
    }
}
