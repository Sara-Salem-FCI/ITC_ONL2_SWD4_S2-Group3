package com.example.fitnessapp.presentation.screens.food_screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessapp.presentation.screens.food_screen.preview.foods
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun FoodBody(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()

    ) {
        items (foods.size) {
            FoodItem(foods[it])
        }
    }
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        FoodBody()
    }
}