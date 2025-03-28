package com.example.fitnessapp.presentation.screens.auth.login_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.presentation.screens.auth.login_screen.viewModel.LoginState
import com.example.fitnessapp.presentation.screens.auth.login_screen.viewModel.LoginViewModel
import com.example.fitnessapp.presentation.screens.auth.components.CustomOutlinedTextField
import com.example.fitnessapp.ui.theme.FitnessAppTheme


@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    goToSignUp: () -> Unit = {}
) {

    val loginViewModel: LoginViewModel = viewModel()

    val state by loginViewModel.state.collectAsStateWithLifecycle()

    val invalidInput by loginViewModel.invalidElements.collectAsStateWithLifecycle()

    val email by loginViewModel.email.collectAsStateWithLifecycle()
    val password by loginViewModel.password.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            is LoginState.Authenticated -> {
                onLogin()
            }

            is LoginState.Error -> {
                Toast.makeText(
                    context,
                    "Authentication failed. ${(state as LoginState.Error).errorMessage}}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> Unit
        }
    }

    Column {
        // Login Form at the center
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Login Title
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Field
            CustomOutlinedTextField(
                value = email,
                onValueChange = {
                    loginViewModel.onEmailChange(it)
                },
                label = "Email",
                errorMessage = invalidInput["email"]
            )

            Spacer(modifier = Modifier.height(12.dp))

            Spacer(modifier = Modifier.height(16.dp))
            //textFieldColors
            // Password Field
            CustomOutlinedTextField(
                value = password,
                onValueChange = {
                    loginViewModel.onPasswordChange(it)
                },
                label = "Password",
                isPassword = true,
                errorMessage = invalidInput["password"]
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Login button
                DefaultButton(
                    text = "Login",
                    color = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        loginViewModel.logInUser()
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Signup Link
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                TextButton(onClick = {
                    // go to signup page
                    goToSignUp()
                }) {
                    Text(
                        text = "Signup",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        LoginScreen(
            onLogin = {}
        )
    }
}
