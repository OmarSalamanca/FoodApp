package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.ui.FoodScreen
import com.example.foodapp.ui.FoodDetailScreen
import com.example.foodapp.LoginScreen
import com.example.foodapp.ui.theme.FoodAppTheme

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
                    ) {
                        composable("login") {
                            LoginScreen(
                                viewModel = loginViewModel,
                                onLoginSuccess = {
                                    navController.navigate("food") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("food") {
                            FoodScreen(
                                navController = navController,
                                modifier = Modifier
                            )
                        }
                        composable("detail/{foodId}") { backStackEntry ->
                            val foodId = backStackEntry.arguments?.getString("foodId") ?: ""
                            FoodDetailScreen(
                                foodId = foodId,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
