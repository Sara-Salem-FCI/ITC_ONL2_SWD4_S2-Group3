package com.example.fitnessapp.presentation.screens.auth.signup_screen.viewModel

sealed class SignUpState {
    data object Loading: SignUpState()
    data object Authenticated: SignUpState()
    data object UnAuthenticated: SignUpState()
    data class Error(val errorMessage: String): SignUpState()

    data class InvalidInput(var invalidInput: Map<String, String>): SignUpState()
}