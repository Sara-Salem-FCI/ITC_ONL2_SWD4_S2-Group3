package com.example.fitnessapp.presentation.screens.dashboared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.navigation.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DiscoverSection(navController: NavController) {

    val auth = FirebaseAuth.getInstance()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            text = "Discover",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            DiscoverButton(
                icon = painterResource(id = R.drawable.moon_svgrepo_com),
                label = "Sleep",
                onClick = { /* Handle click */ }
            )
            DiscoverButton(
                icon = painterResource(id = R.drawable.dinner_icon),
                label = "Recipes",
                onClick = {
                    navController.navigate(Screens.FoodScreen.route)
                }
            )
            DiscoverButton(
                icon = painterResource(id = R.drawable.baseline_fitness_center_24),
                label = "Workouts",
                onClick = { /* Handle click */ }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            DiscoverButton(
                icon = painterResource(id = R.drawable.moon_svgrepo_com),
                label = "Sync up",
                onClick = { /* Handle click */ }
            )
            DiscoverButton(
                icon = painterResource(id = R.drawable.moon_svgrepo_com),
                label = "Friends",
                onClick = { /* Handle click */ }
            )
            DiscoverButton(
                icon = painterResource(id = R.drawable.moon_svgrepo_com),
                label = "Community",
                onClick = {
                    auth.signOut()
                    navController.navigate(Screens.LogInScreen.route) {
                        popUpTo(Screens.DashBoardScreen.route)
                    }
                }
            )
        }
    }
}