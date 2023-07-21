package com.codingbloom.loginflowjetpack.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codingbloom.loginflowjetpack.data.LoginUiEvent
import com.codingbloom.loginflowjetpack.data.LoginUiState
import com.codingbloom.loginflowjetpack.data.rules.Validator
import com.codingbloom.loginflowjetpack.navigation.AppRoutes
import com.codingbloom.loginflowjetpack.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class LoginViewModel : ViewModel() {

    private val TAG = "TAG"

    val loginUiState = mutableStateOf(LoginUiState())

    val allValidationsPassed = mutableStateOf(false)

    val loginInProgress = mutableStateOf(false)




    fun onEvent(event: LoginUiEvent){

        when(event){


            is LoginUiEvent.EmailChanged -> {

                loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )
            }

            is LoginUiEvent.PasswordChanged -> {

                loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
            }

            is LoginUiEvent.LoginButtonClicked -> login()
        }

        validateDataWithRules()
    }

    private fun validateDataWithRules() {

        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUiState.value.password
        )


        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        //allValidation.value = true, if only all the conditions are true
        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login(){

        loginInProgress.value = true

        val email = loginUiState.value.email
        val password = loginUiState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{



                if (it.isSuccessful){
                    loginInProgress.value = false
                    AppRoutes.navigateTo(Screen.HomeScreenRoute)
                }
            }
            .addOnFailureListener{
                loginInProgress.value = false
                Log.d(TAG, "Exception-localizedMessage: ${it.localizedMessage}")
            }
    }
}