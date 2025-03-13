package com.example.fitnessapp.ui.screens.login_screen

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
import com.example.fitnessapp.ui.components.DefaultButton
import com.example.fitnessapp.ui.screens.signup_screen.components.CustomOutlinedTextField
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import com.google.firebase.auth.FirebaseAuth

val auth: FirebaseAuth = FirebaseAuth.getInstance()

@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    goToSignUp: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Error states
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val context = LocalContext.current

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
                    email = it
                    if (it.contains("@") && it.contains(".")) emailError = ""
                },
                label = "Email",
                errorMessage = emailError
            )

            Spacer(modifier = Modifier.height(12.dp))

            Spacer(modifier = Modifier.height(16.dp))
            //textFieldColors
            // Password Field
            CustomOutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    if (it.isNotBlank()) passwordError = ""
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
                // Login button
                DefaultButton(
                    text = "Login",
                    color = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        // Validation
                        emailError =
                            if (!email.contains("@") || !email.contains(".")) "Invalid email format" else ""
                        passwordError =
                            if (password.length < 6) "Password must be at least 6 characters" else ""

                        // If no errors, proceed with login
                        if (emailError.isEmpty() && passwordError.isEmpty()) {
                            signInUser(email, password, context, onLogin)
                        }
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

fun signInUser(email: String, password: String, context: android.content.Context, onLogin: () -> Unit) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("TAG", "signInWithEmail:success")
                onLogin()
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                Log.w("TAG", "signInWithEmail:failure", task.exception)
                Toast.makeText(context, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
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
