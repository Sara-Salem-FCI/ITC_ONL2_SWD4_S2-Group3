package com.example.fitnessapp.presentation.navigation

sealed class Screens(val route: String) {

    data object SplashScreen: Screens("splash")

    data object SignUpScreen: Screens("sign_up")

    data object LogInScreen: Screens("login")

    data object LevelScreen: Screens("level")

    data object FoodScreen: Screens("food")

    data object HeightScreen: Screens("height")

    data object GenderScreen: Screens("gender")

    data object DashBoardScreen: Screens("dashBoard")

    data object WeightScreen: Screens("weight")

    data object SetGoalsScreen: Screens("set_goals")

    data object ExerciseScreen: Screens("exercises")

    data object WaterScreen: Screens("water")

    data object SearchBtnScreen: Screens("search")

    data object FoodSearchScreen: Screens("food_Search")

    data object HealthScreen: Screens("health")

    data object ProfileScreen: Screens("profile")

    data object ScanFoodScreen: Screens("scan_food")
}