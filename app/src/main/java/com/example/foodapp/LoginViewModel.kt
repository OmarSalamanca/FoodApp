package com.example.foodapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class LoginViewModel : ViewModel () {
    private val _username = mutableStateOf("")
    val username : State<String> = _username

    private val _password = mutableStateOf("")
    val password : State<String> = _password

    fun usernameTyped(newUsername: String) {
        _username.value = newUsername
    }

    fun passwordTyped(newPassword: String) {
        _password.value = newPassword
    }

    fun login(): Boolean {
        return _username.value == "jdeed" && _password.value == "1234"
    }
}