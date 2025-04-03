package com.example.fitnessapp.presentation.screens.auth.login_screen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow<LoginState>(LoginState.UnAuthenticated)
    val state = _state.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _invalidElements = MutableStateFlow<Map<String, String>>(emptyMap())
    val invalidElements = _invalidElements.asStateFlow()

    fun logInUser() {
        val errors = mutableMapOf<String, String>()

        if (!_email.value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}\$".toRegex())) {
            errors["email"] = "Invalid email format"
        }

        if (_password.value.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        if (errors.isEmpty()) {
            _invalidElements.value = errors
            _state.value = LoginState.InvalidInput(errors)
            signInUser(_email.value, _password.value)
        } else {
            _invalidElements.value = errors
            _state.value = LoginState.InvalidInput(errors)
        }
    }

    private fun signInUser(
        email: String,
        password: String,
    ) {
        _state.value = LoginState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    _state.value = LoginState.Authenticated
                } else {
                    _state.value = LoginState.Error(task.exception.toString())
                    Log.w("TAG", "signInWithEmail:failure", task.exception)

                }
            }
    }

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }
}