package com.example.fitnessapp.presentation.navigation

import GenderScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.presentation.screens.dashboared.ProfileScreen
import com.example.fitnessapp.presentation.screens.food_screen.FoodScreen
import com.example.fitnessapp.presentation.screens.height_select.NumberPickerDemo
import com.example.fitnessapp.presentation.screens.level_screen.PhysicalActivityLevel
import com.example.fitnessapp.presentation.screens.auth.login_screen.LoginScreen
import com.example.fitnessapp.presentation.screens.auth.signup_screen.SignUpScreen
import com.example.fitnessapp.presentation.screens.set_goals_screen.SetGoalsScreen
import com.example.fitnessapp.presentation.screens.splash_screen.SplashScreen
import com.example.fitnessapp.presentation.screens.weight.WeightScreen
import com.google.firebase.auth.FirebaseAuth

/**
 * the Navigation Graph
 * SignUp -> Gender -> Height -> Level -> Weight -> SetGoal -> DashBoard
 * Login -> DashBoard
 */

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser

//    LaunchedEffect(currentUser) {
//        if (currentUser != null) {
//            navController.navigate(Screens.DashBoardScreen.route) {
//                popUpTo(Screens.SplashScreen.route) { inclusive = true }
//            }
//        } else {
//            navController.navigate(Screens.LogInScreen.route)
//        }
//    }


    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) Screens.DashBoardScreen.route else Screens.SplashScreen.route,
        modifier = modifier
    ) {
        composable(Screens.SplashScreen.route) {
            SplashScreen {
                navController.navigate(Screens.LogInScreen.route) {
                    popUpTo(Screens.SplashScreen.route) { inclusive = true }
                }
            }
        }

        composable(Screens.LogInScreen.route) {
            LoginScreen(
                onLogin = {
                    navController.navigate(Screens.DashBoardScreen.route) {
                        popUpTo(0)
                    }
                },
                goToSignUp = {
                    navController.navigate(Screens.SignUpScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        composable(Screens.SignUpScreen.route) {
            SignUpScreen(
                onSignUp = { username, email, password ->
                    navController.navigate(Screens.GenderScreen.route) {
                        popUpTo(0)
                    }
                },
                goToLogin = {
                    navController.navigate(Screens.LogInScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        composable(Screens.DashBoardScreen.route) {
            ProfileScreen(
                navController
            )
        }

        composable(Screens.LevelScreen.route) {
            PhysicalActivityLevel(
                onPersonLevel = { personLevel ->
                    navController.navigate(Screens.WeightScreen.route)
                }
            )
        }

        composable(Screens.GenderScreen.route) {
            GenderScreen { gender ->
                navController.navigate(Screens.HeightScreen.route)
            }
        }

        composable(Screens.WeightScreen.route) {
            WeightScreen(
                onWeight = {
                    navController.navigate(Screens.SetGoalsScreen.route)
                }
            )
        }

        composable(Screens.SetGoalsScreen.route) {
            SetGoalsScreen(
                onSetGoals = {
                    navController.navigate(Screens.DashBoardScreen.route) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screens.FoodScreen.route) {
            FoodScreen()
        }



        composable(Screens.HeightScreen.route) {
            NumberPickerDemo(
                onHeight = {
                    navController.navigate(Screens.LevelScreen.route)
                }
            )
        }
    }
}
