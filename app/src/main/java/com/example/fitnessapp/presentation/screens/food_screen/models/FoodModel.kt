package com.example.fitnessapp.presentation.screens.food_screen.models

data class FoodModel(
    val recipeName: String,
    val description: String,
    var isBookMark: Boolean = false,
    val recipeImage: Int
)
