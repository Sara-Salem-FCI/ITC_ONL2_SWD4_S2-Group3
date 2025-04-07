package com.example.exercisesgrid

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "exercises"
    ) {
        composable("exercises") {
            ExercisesScreen(navController = navController)
        }
        composable("exerciseDetails/{muscle}") { backStackEntry ->
            val muscle = backStackEntry.arguments?.getString("muscle") ?: ""
            ExerciseDetailScreen(muscle = muscle)
        }
    }
}