package com.codingbloom.loginflowjetpack.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){

    object SignUpScreenRoute : Screen()
    object TermsAndConditionsScreenRoute : Screen()
    object LoginScreenRoute : Screen()
    object HomeScreenRoute : Screen()
}

object AppRoutes {

    val currentScreen : MutableState<Screen> = mutableStateOf(Screen.SignUpScreenRoute)

    fun navigateTo(destination: Screen) {

        currentScreen.value = destination
    }
}