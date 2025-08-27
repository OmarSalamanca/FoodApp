package com.example.foodapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.foodapp.FoodDetailViewModel
import com.example.foodapp.FoodDetail

@Composable
fun FoodDetailScreen(
    foodId: String,
    onBack: () -> Unit,
    viewModel: FoodDetailViewModel = viewModel()
) {

    LaunchedEffect(foodId) {
        viewModel.fetchFoodDetail(foodId)
    }

    val foodDetail by viewModel.foodDetail.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDFDFD))
    ) {
        when {
            foodDetail == null -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {

                        Button(
                            onClick = { onBack() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFFFFF),
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Text("<",
                                fontSize = 24.sp)
                        }
                    }

                    item {

                        AsyncImage(
                            model = foodDetail!!.strMealThumb,
                            contentDescription = foodDetail!!.strMeal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                        )
                    }

                    item {

                        Text(
                            text = foodDetail!!.strMeal,
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.Black
                        )
                    }


                    items(foodDetail!!.ingredients) { ingredient ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                    println("Clicked ingredient: $ingredient")
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Text(
                                text = ingredient,
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }


                    item {
                        Text(
                            text = "Instructions:",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text(
                            text = foodDetail!!.strInstructions,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
