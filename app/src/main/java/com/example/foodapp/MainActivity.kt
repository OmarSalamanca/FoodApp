package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.foodapp.ui.theme.FoodAppTheme
import com.example.foodapp.ui.FoodScreen


class MainActivity : ComponentActivity() {


    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
                LoginScreen(viewModel = loginViewModel)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    FoodScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    FoodAppTheme {

        LoginScreen(viewModel = LoginViewModel())
    }
}
