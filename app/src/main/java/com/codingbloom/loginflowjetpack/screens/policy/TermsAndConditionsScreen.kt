package com.codingbloom.loginflowjetpack.screens.policy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingbloom.loginflowjetpack.components.HeadingTextComponent
import com.codingbloom.loginflowjetpack.navigation.AppRoutes
import com.codingbloom.loginflowjetpack.navigation.Screen
import com.codingbloom.loginflowjetpack.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        HeadingTextComponent(value = "Terms and Conditions Screen")
    }

    SystemBackButtonHandler {
        AppRoutes.navigateTo(Screen.SignUpScreenRoute)
    }
}

@Preview
@Composable
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen()
}