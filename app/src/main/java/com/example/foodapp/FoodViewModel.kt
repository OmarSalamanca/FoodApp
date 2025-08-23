package com.example.foodapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class FoodViewModel : ViewModel() {


    private val repository = FoodRepository()

    private val _meals = mutableStateOf<List<FoodItem>>(emptyList())
    val meals: State<List<FoodItem>> = _meals

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun fetchMeals() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = repository.getFoodMenu()
                _meals.value = response.meals
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}
