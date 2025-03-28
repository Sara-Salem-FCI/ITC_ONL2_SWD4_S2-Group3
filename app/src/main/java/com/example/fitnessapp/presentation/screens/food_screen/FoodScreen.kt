package com.example.fitnessapp.presentation.screens.food_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.presentation.components.TopBar
import com.example.fitnessapp.presentation.screens.food_screen.components.FoodBody
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun FoodScreen() {
    FoodBody()
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        FoodScreen()
    }
}