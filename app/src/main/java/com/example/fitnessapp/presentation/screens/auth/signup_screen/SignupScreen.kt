package com.example.fitnessapp.presentation.screens.auth.signup_screen

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
import com.example.fitnessapp.presentation.screens.auth.components.CustomOutlinedTextField
import com.example.fitnessapp.presentation.screens.auth.signup_screen.viewModel.SignUpState
import com.example.fitnessapp.presentation.screens.auth.signup_screen.viewModel.SignUpViewModel
import com.example.fitnessapp.ui.theme.FitnessAppTheme


@Composable
fun SignUpScreen(
    onSignUp: (String, String, String) -> Unit,
    goToLogin: () -> Unit = {}
) {
    val signUpViewModel: SignUpViewModel = viewModel()
    val state by signUpViewModel.state.collectAsStateWithLifecycle()

    val errorMessages by signUpViewModel.invalidElements.collectAsStateWithLifecycle()

    val userName by signUpViewModel.userName.collectAsStateWithLifecycle()
    val email by signUpViewModel.email.collectAsStateWithLifecycle()
    val password by signUpViewModel.password.collectAsStateWithLifecycle()


    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            SignUpState.Authenticated -> {
                onSignUp(userName, email, password)
            }

            is SignUpState.Error -> {
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Username Field
            CustomOutlinedTextField(
                value = userName,
                onValueChange = {
                    signUpViewModel.onUserNameChange(it)
                },
                label = "Username",
                errorMessage = errorMessages["userName"] ?: ""
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email Field
            CustomOutlinedTextField(
                value = email,
                onValueChange = {
                    signUpViewModel.onEmailChange(it)
                },
                label = "Email",
                errorMessage = errorMessages["email"] ?: ""
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password Field
            CustomOutlinedTextField(
                value = password,
                onValueChange = {
                    signUpViewModel.onPasswordChange(it)
                },
                label = "Password",
                isPassword = true,
                errorMessage = errorMessages["password"] ?: ""
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DefaultButton(
                    text = "Get Started",
                    color = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        // Validation
                        signUpViewModel.createUser()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                TextButton(onClick = {
                    // go to login page
                    goToLogin()
                }) {
                    Text(
                        text = "Login",
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
        SignUpScreen(
            onSignUp = { _, _, _ -> }
        )
    }
}