package com.example.fitnessapp.presentation.navigation

import GenderScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.presentation.components.TopBar
import com.example.fitnessapp.presentation.components.TopBarWithLogo
import com.example.fitnessapp.presentation.screens.dashboared.ProfileScreen
import com.example.fitnessapp.presentation.screens.food_screen.FoodScreen
import com.example.fitnessapp.presentation.screens.userdata.height_select.NumberPickerDemo
import com.example.fitnessapp.presentation.screens.userdata.level_screen.PhysicalActivityLevel
import com.example.fitnessapp.presentation.screens.auth.login_screen.LoginScreen
import com.example.fitnessapp.presentation.screens.auth.signup_screen.SignUpScreen
import com.example.fitnessapp.presentation.screens.dashboared.components.ProfileTopBar
import com.example.fitnessapp.presentation.screens.userdata.set_goals_screen.SetGoalsScreen
import com.example.fitnessapp.presentation.screens.splash_screen.SplashScreen
import com.example.fitnessapp.presentation.screens.userdata.weight.WeightScreen
import com.google.firebase.auth.FirebaseAuth

/**
 * the Navigation Graph
 * SignUp -> Gender -> Height -> Level -> Weight -> SetGoal -> DashBoard
 * Login -> DashBoard
 */

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    val topBar = remember { mutableStateOf("") }

    val currentUser = FirebaseAuth.getInstance().currentUser

    Scaffold(
        topBar = {
            when (topBar.value) {
                "TopBarWithLogo" -> {
                    TopBarWithLogo()
                }

                "profile" -> {
                    ProfileTopBar()
                }

                "food" -> {
                    TopBar("Food")
                }

                else -> Unit
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = if (currentUser != null) Screens.DashBoardScreen.route else Screens.SplashScreen.route,
            modifier = modifier.padding(paddingValues)
        ) {

            composable(Screens.SplashScreen.route) {
                SplashScreen {
                    navController.navigate(Screens.LogInScreen.route) {
                        popUpTo(Screens.SplashScreen.route) { inclusive = true }
                    }
                }
            }

            composable(Screens.LogInScreen.route) {
                topBar.value = "TopBarWithLogo"

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
                topBar.value = "TopBarWithLogo"

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
                topBar.value = "profile"
                ProfileScreen(
                    navController
                )
            }

            composable(Screens.LevelScreen.route) {
                topBar.value = "TopBarWithLogo"

                PhysicalActivityLevel(
                    onPersonLevel = { personLevel ->
                        navController.navigate(Screens.WeightScreen.route)
                    }
                )
            }

            composable(Screens.GenderScreen.route) {
                topBar.value = "TopBarWithLogo"

                GenderScreen { gender ->
                    navController.navigate(Screens.HeightScreen.route)
                }
            }

            composable(Screens.WeightScreen.route) {
                topBar.value = "TopBarWithLogo"

                WeightScreen(
                    onWeight = {
                        navController.navigate(Screens.SetGoalsScreen.route)
                    }
                )
            }

            composable(Screens.SetGoalsScreen.route) {
                topBar.value = "TopBarWithLogo"

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
                topBar.value = "food"
                FoodScreen()
            }



            composable(Screens.HeightScreen.route) {
                topBar.value = "TopBarWithLogo"
                NumberPickerDemo(
                    onHeight = {
                        navController.navigate(Screens.LevelScreen.route)
                    }
                )
            }
        }
    }
}