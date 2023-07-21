package com.codingbloom.loginflowjetpack.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codingbloom.loginflowjetpack.R
import com.codingbloom.loginflowjetpack.components.ButtonComponent
import com.codingbloom.loginflowjetpack.components.ClickableLoginTextComponent
import com.codingbloom.loginflowjetpack.components.DividerTextComponent
import com.codingbloom.loginflowjetpack.components.HeadingTextComponent
import com.codingbloom.loginflowjetpack.components.MyPasswordTextField
import com.codingbloom.loginflowjetpack.components.MyTextFieldComponent
import com.codingbloom.loginflowjetpack.components.NormalTextComponent
import com.codingbloom.loginflowjetpack.components.UnderlineTextComponent
import com.codingbloom.loginflowjetpack.data.LoginUiEvent
import com.codingbloom.loginflowjetpack.navigation.AppRoutes
import com.codingbloom.loginflowjetpack.navigation.Screen
import com.codingbloom.loginflowjetpack.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(28.dp),
                content = {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            NormalTextComponent(value = stringResource(id = R.string.login))
                            HeadingTextComponent(value = stringResource(id = R.string.welcome))

                            Spacer(modifier = Modifier.height(20.dp))

                            MyTextFieldComponent(
                                labelValue = stringResource(id = R.string.email),
                                onTextChanged = {
                                    loginViewModel.onEvent(LoginUiEvent.EmailChanged(it))
                                },
                                painterResource = painterResource(id = R.drawable.message),
                                errorStatus = loginViewModel.loginUiState.value.emailError
                            )

                            MyPasswordTextField(
                                labelValue = stringResource(id = R.string.password),
                                onPasswordChanged = {
                                    loginViewModel.onEvent(LoginUiEvent.PasswordChanged(it))
                                },
                                painterResource = painterResource(id = R.drawable.ic_lock),
                                errorStatus = loginViewModel.loginUiState.value.passwordError
                            )

                            Spacer(modifier = Modifier.height(40.dp))

                            UnderlineTextComponent(value = stringResource(id = R.string.forgot_password))

                            Spacer(modifier = Modifier.height(40.dp))

                            ButtonComponent(
                                value = stringResource(id = R.string.login),
                                onButtonClicked = {
                                      loginViewModel.onEvent(LoginUiEvent.LoginButtonClicked)
                                },
                                isEnabled = loginViewModel.allValidationsPassed.value
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            DividerTextComponent()

                            ClickableLoginTextComponent(
                                tryingToLogin = false,
                                onTextSelected = {
                                    AppRoutes.navigateTo(destination = Screen.SignUpScreenRoute)
                                }
                            )
                        }
                    )
                }
            )

            if (loginViewModel.loginInProgress.value)
                CircularProgressIndicator()
        }
    )


    SystemBackButtonHandler {AppRoutes.navigateTo(destination = Screen.SignUpScreenRoute)}
}

@Preview
@Composable
fun DefaultPreviewOfLoginScreen(){
    LoginScreen()
}