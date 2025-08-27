package com.example.foodapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodDetailViewModel(
    private val repository: FoodRepository = FoodRepository()
) : ViewModel() {


    private val _foodDetail = MutableStateFlow<FoodDetail?>(null)
    val foodDetail: StateFlow<FoodDetail?> = _foodDetail


    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun fetchFoodDetail(foodId: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val detail = repository.getFoodDetail(foodId)
                _foodDetail.value = detail
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}
