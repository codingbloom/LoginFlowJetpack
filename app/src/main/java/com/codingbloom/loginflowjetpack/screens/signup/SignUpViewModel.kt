package com.codingbloom.loginflowjetpack.screens.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codingbloom.loginflowjetpack.data.RegistrationUiState
import com.codingbloom.loginflowjetpack.data.SignUpUiEvent
import com.codingbloom.loginflowjetpack.data.rules.Validator
import com.codingbloom.loginflowjetpack.navigation.AppRoutes
import com.codingbloom.loginflowjetpack.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignUpViewModel : ViewModel() {

    private val TAG = "TAG"

    val registrationUiState = mutableStateOf(RegistrationUiState())

    val allValidationsPassed = mutableStateOf(false)

    val signUpInProgress = mutableStateOf(false)




    fun onEvent(event: SignUpUiEvent){

        when(event){

            is SignUpUiEvent.FirstNameChanged -> {

                registrationUiState.value = registrationUiState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignUpUiEvent.LastNameChanged -> {

                registrationUiState.value = registrationUiState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignUpUiEvent.EmailChanged -> {

                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignUpUiEvent.PasswordChanged -> {

                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignUpUiEvent.PrivacyPolicyCheckBoxClicked -> {

                registrationUiState.value = registrationUiState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            is SignUpUiEvent.RegisterButtonClicked -> signUp()
        }

        validateDataWithRules()
    }

    private fun signUp(){

        Log.d(TAG, "Inside_signUp")
        printState()

        createUserInFirebase(
            email = registrationUiState.value.email,
            password = registrationUiState.value.password
        )
    }

    private fun validateDataWithRules() {

        val fNameResult = Validator.validateFirstName(
            fName = registrationUiState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUiState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUiState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUiState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUiState.value.privacyPolicyAccepted
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult: $fNameResult")
        Log.d(TAG, "lNameResult: $lNameResult")
        Log.d(TAG, "emailResult: $emailResult")
        Log.d(TAG, "passwordResult: $passwordResult")
        Log.d(TAG, "privacyPolicyResult: $privacyPolicyResult")

        registrationUiState.value = registrationUiState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        //allValidation.value = true, if only all the conditions are true
        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status
    }

    private fun printState(){

        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUiState.value.toString())
    }

    private fun createUserInFirebase(email: String, password: String)
    {
        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, "isSuccessful: ${it.isSuccessful}")

                signUpInProgress.value = false

                if (it.isSuccessful) AppRoutes.navigateTo(Screen.HomeScreenRoute)
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception-message: ${it.message}")
                Log.d(TAG, "Exception-localizedMessage: ${it.localizedMessage}")
            }
    }


}