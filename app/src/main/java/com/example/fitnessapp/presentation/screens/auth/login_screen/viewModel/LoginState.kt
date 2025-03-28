package com.example.fitnessapp.presentation.screens.auth.login_screen.viewModel

sealed class LoginState {
    data object UnAuthenticated : LoginState()
    data object Authenticated: LoginState()

    data object Loading: LoginState()

    data class Error(val errorMessage: String = ""): LoginState()
    data class InvalidInput(val invalidInput: Map<String, String>): LoginState()
}