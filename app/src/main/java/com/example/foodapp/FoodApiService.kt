    package com.example.foodapp

    import retrofit2.http.GET
    import retrofit2.http.Query

    interface FoodApiService {
        @GET("filter.php?a=Mexican")
        suspend fun getFoodMenu(): FoodResponse

        @GET("lookup.php")
        suspend fun getFoodDetail(@Query("i") foodId: String): FoodDetailResponse
    }