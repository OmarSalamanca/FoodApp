package com.example.foodapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color




@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val username by viewModel.username
    val password by viewModel.password
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { viewModel.usernameTyped(it) },
            label = { Text("Username") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { viewModel.passwordTyped(it) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { loginResult = viewModel.login() }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        loginResult?.let {
            Text(
                text = if (it) "Login Successful" else "Login Failed",
                color = if (it) Color.Green else Color.Red
        }
        }
    }
}


