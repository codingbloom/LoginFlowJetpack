package com.codingbloom.loginflowjetpack.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codingbloom.loginflowjetpack.navigation.AppRoutes
import com.codingbloom.loginflowjetpack.navigation.Screen
import com.codingbloom.loginflowjetpack.screens.home.HomeScreen
import com.codingbloom.loginflowjetpack.screens.home.HomeViewModel
import com.codingbloom.loginflowjetpack.screens.login.LoginScreen
import com.codingbloom.loginflowjetpack.screens.signup.SignUpScreen
import com.codingbloom.loginflowjetpack.screens.policy.TermsAndConditionsScreen

@Composable
fun AppMainContent(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
        content = {

            if (homeViewModel.isUserLoggedIn.value == true){
                AppRoutes.navigateTo(destination = Screen.HomeScreenRoute)
            }
            else {
                AppRoutes.navigateTo(destination = Screen.SignUpScreenRoute)
            }

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