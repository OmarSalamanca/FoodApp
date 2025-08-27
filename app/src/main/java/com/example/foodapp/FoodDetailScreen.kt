package com.example.foodapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.foodapp.FoodDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
                .background(Color(0xFFFFFFFF))
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            Column {
                TopAppBar(modifier = Modifier.height(72.dp),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFF3F1F1)
                    ),
                    navigationIcon = {
//                Column(
//                    modifier = Modifier
//                        .wrapContentSize()
//                        .background(Color.Blue),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                )
//                {
                        IconButton(
                            onClick = { onBack() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.size(28.dp)
                                    .offset(y = -11.dp)

                            )
                        }
//                }
                    },
                    title = {},
                    actions = {}
                )
            when {
                foodDetail == null -> {
                    CircularProgressIndicator(modifier = Modifier/*.align(Alignment.Center)*/)
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp),
                                shape = MaterialTheme.shapes.medium,
                                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
                            )
                            {
                                AsyncImage(
                                    model = foodDetail!!.strMealThumb,
                                    contentDescription = foodDetail!!.strMeal,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(220.dp),
                                    contentScale = ContentScale.FillWidth
                                )
                            }

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
}
