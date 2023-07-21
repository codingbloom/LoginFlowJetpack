package com.codingbloom.loginflowjetpack.screens.signup

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
import com.codingbloom.loginflowjetpack.components.CheckBoxComponent
import com.codingbloom.loginflowjetpack.components.ClickableLoginTextComponent
import com.codingbloom.loginflowjetpack.components.DividerTextComponent
import com.codingbloom.loginflowjetpack.components.HeadingTextComponent
import com.codingbloom.loginflowjetpack.components.MyPasswordTextField
import com.codingbloom.loginflowjetpack.components.MyTextFieldComponent
import com.codingbloom.loginflowjetpack.components.NormalTextComponent
import com.codingbloom.loginflowjetpack.data.SignUpUiEvent
import com.codingbloom.loginflowjetpack.navigation.AppRoutes
import com.codingbloom.loginflowjetpack.navigation.Screen
import com.codingbloom.loginflowjetpack.R.string as StringResource

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel()
) {

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
                            NormalTextComponent(value = stringResource(id = StringResource.hello))
                            HeadingTextComponent(value = stringResource(id = R.string.create_account))

                            Spacer(modifier = Modifier.height(20.dp))

                            MyTextFieldComponent(
                                labelValue = stringResource(id = R.string.first_name),
                                painterResource = painterResource(id = R.drawable.profile),
                                onTextChanged = {
                                    signUpViewModel.onEvent(SignUpUiEvent.FirstNameChanged(it))
                                },
                                errorStatus = signUpViewModel.registrationUiState.value.firstNameError
                            )

                            MyTextFieldComponent(
                                labelValue = stringResource(id = R.string.last_name),
                                painterResource = painterResource(id = R.drawable.profile),
                                onTextChanged = {
                                    signUpViewModel.onEvent(SignUpUiEvent.LastNameChanged(it))
                                },
                                errorStatus = signUpViewModel.registrationUiState.value.lastNameError
                            )

                            MyTextFieldComponent(
                                labelValue = stringResource(id = R.string.email),
                                painterResource = painterResource(id = R.drawable.message),
                                onTextChanged = {
                                    signUpViewModel.onEvent(SignUpUiEvent.EmailChanged(it))
                                },
                                errorStatus = signUpViewModel.registrationUiState.value.emailError
                            )

                            MyPasswordTextField(
                                labelValue = stringResource(id = R.string.password),
                                painterResource = painterResource(id = R.drawable.ic_lock),
                                onPasswordChanged = {
                                    signUpViewModel.onEvent(SignUpUiEvent.PasswordChanged(password = it))
                                },
                                errorStatus = signUpViewModel.registrationUiState.value.passwordError
                            )

                            CheckBoxComponent(
                                onTextSelected = { AppRoutes.navigateTo(Screen.TermsAndConditionsScreenRoute) },
                                onCheckedChange = {
                                    signUpViewModel.onEvent(SignUpUiEvent.PrivacyPolicyCheckBoxClicked(status = it))
                                }
                            )

                            Spacer(modifier = Modifier.height(40.dp))

                            ButtonComponent(
                                value = stringResource(id = R.string.register),
                                onButtonClicked = {
                                    signUpViewModel.onEvent(SignUpUiEvent.RegisterButtonClicked)
                                },
                                isEnabled = signUpViewModel.allValidationsPassed.value
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            DividerTextComponent()

                            ClickableLoginTextComponent(
                                tryingToLogin = true,
                                onTextSelected = {
                                    AppRoutes.navigateTo(destination = Screen.LoginScreenRoute)
                                }
                            )
                        }
                    )
                }
            )

            if (signUpViewModel.signUpInProgress.value)
                CircularProgressIndicator()
        }
    )


}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}
