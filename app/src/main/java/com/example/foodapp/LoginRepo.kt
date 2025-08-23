package com.example.foodapp

class LoginRepo {
    fun login(useranme: String, password: String): Boolean {

        return (useranme == "jdeed" && password == "1234")
    }
}