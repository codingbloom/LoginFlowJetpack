package com.codingbloom.loginflowjetpack.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.codingbloom.loginflowjetpack.navigation.AppRoutes
import com.codingbloom.loginflowjetpack.navigation.Screen
import com.codingbloom.loginflowjetpack.screens.home.HomeScreen
import com.codingbloom.loginflowjetpack.screens.login.LoginScreen
import com.codingbloom.loginflowjetpack.screens.signup.SignUpScreen
import com.codingbloom.loginflowjetpack.screens.policy.TermsAndConditionsScreen

@Composable
fun AppMainContent() {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
        content = {

            Crossfade(
                targetState = AppRoutes.currentScreen,
                content = { currentState ->

                    when(currentState.value) {
                        is Screen.SignUpScreenRoute -> SignUpScreen()

                        is Screen.TermsAndConditionsScreenRoute -> TermsAndConditionsScreen()

                        is Screen.LoginScreenRoute -> LoginScreen()

                        is Screen.HomeScreenRoute -> HomeScreen()
                    }
                },
                label = ""
            )
        }
    )
}