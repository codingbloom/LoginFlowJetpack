package com.codingbloom.loginflowjetpack.data

data class LoginUiState(

    val email: String = "",
    val password: String = "",

    val emailError: Boolean = false,
    val passwordError: Boolean = false
)
