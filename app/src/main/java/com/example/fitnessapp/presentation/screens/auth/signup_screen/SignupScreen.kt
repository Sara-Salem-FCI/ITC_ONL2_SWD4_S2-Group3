package com.example.fitnessapp.presentation.screens.auth.signup_screen

import android.util.Log
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
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.presentation.screens.auth.signup_screen.components.CustomOutlinedTextField
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import com.google.firebase.auth.FirebaseAuth

val auth: FirebaseAuth = FirebaseAuth.getInstance()

@Composable
fun SignUpScreen(
    onSignUp: (String, String, String) -> Unit,
    goToLogin: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Error states
    var usernameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val context = LocalContext.current

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
                value = username,
                onValueChange = {
                    username = it
                    if (it.isNotBlank()) usernameError = ""
                },
                label = "Username",
                errorMessage = usernameError
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Email Field
            CustomOutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    if (it.contains("@") && it.contains(".")) emailError = ""
                },
                label = "Email",
                errorMessage = emailError
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password Field
            CustomOutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    if (it.length >= 6) passwordError = ""
                },
                label = "Password",
                isPassword = true,
                errorMessage = passwordError
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
                        usernameError = if (username.isBlank()) "Username cannot be empty" else ""
                        emailError =
                            if (!email.contains("@") || !email.contains(".")) "Invalid email format" else ""
                        passwordError =
                            if (password.length < 6) "Password must be at least 6 characters" else ""

                        // If no errors, proceed with signup
                        if (usernameError.isEmpty() && emailError.isEmpty() && passwordError.isEmpty()) {
                            createUser(email, password, context)
                            onSignUp(username, email, password)
                        }
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

fun createUser(email: String, password: String, context: android.content.Context) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("TAG", "createUserWithEmail:success")
                val user = auth.currentUser

            } else {
                Log.w("TAG", "createUserWithEmail:failure", task.exception)
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
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