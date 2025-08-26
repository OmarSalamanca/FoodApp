package com.example.foodapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodDetailViewModel(
    private val repository: FoodRepository = FoodRepository()
) : ViewModel() {

    // Holds the meal detail
    private val _foodDetail = MutableStateFlow<FoodDetail?>(null)
    val foodDetail: StateFlow<FoodDetail?> = _foodDetail

    // Loading state
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    // Error state
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Function to fetch detail of a meal by its ID
    fun fetchFoodDetail(foodId: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val detail = repository.getFoodDetail(foodId)   // ✅ calls your repository
                _foodDetail.value = detail
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}
