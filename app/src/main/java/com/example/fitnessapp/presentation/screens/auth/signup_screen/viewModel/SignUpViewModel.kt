package com.example.fitnessapp.presentation.screens.auth.signup_screen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel : ViewModel() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow<SignUpState>(SignUpState.UnAuthenticated)
    val state = _state.asStateFlow()

    // for invalid inputs
    private val _invalidElements = MutableStateFlow<Map<String, String>>(emptyMap())
    val invalidElements = _invalidElements.asStateFlow()

    // for user info
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun createUser() {
        val errors = mutableMapOf<String, String>()

        if (!_userName.value.matches("^[a-zA-Z0-9._]{3,20}$".toRegex())) {
            errors["userName"] = "Username cannot be empty"
        }

        if (!_email.value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}\$".toRegex())) {
            errors["email"] = "Invalid email format"
        }

        if (_password.value.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        if (errors.isEmpty()) {
            _invalidElements.value = errors
            _state.value = SignUpState.InvalidInput(errors)
            uploadUserInfo(_email.value, _password.value, _userName.value)
        } else {
            _invalidElements.value = errors
            _state.value = SignUpState.InvalidInput(errors)
        }
    }


    private fun uploadUserInfo(email: String, password: String, userName: String) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            _state.value = SignUpState.Loading

            if (task.isSuccessful) {
                Log.d("TAG", "createUserWithEmail:success")
                val user = auth.currentUser
                _state.value = SignUpState.Authenticated
            } else {
                Log.w("TAG", "createUserWithEmail:failure", task.exception)
                _state.value = SignUpState.Error(task.exception.toString())
            }
        }
    }

    fun onUserNameChange(userName: String) {
        _userName.value = userName
    }

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

}
