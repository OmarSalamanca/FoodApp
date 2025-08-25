package com.example.foodapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.foodapp.FoodViewModel
import com.example.foodapp.FoodItem

@Composable
fun FoodScreen(
    modifier: Modifier = Modifier,
    viewModel: FoodViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchMeals()
    }

    val meals by viewModel.meals
    val loading by viewModel.loading
    val error by viewModel.error

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(color = 0xFFAD261E))

    ) {
        when {
            loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            error != null -> {
                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(meals) { meal: FoodItem ->
                        MealItem(meal)
                    }
                }
            }
        }
    }
}

@Composable
fun MealItem(
    meal: FoodItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

    Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = meal.strMeal,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = meal.strMeal,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
