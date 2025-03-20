package com.example.fitnessapp.presentation.screens.food_screen.preview

import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.screens.food_screen.models.FoodModel

val food1 = FoodModel(
    recipeName = "Ta3mya",
    description = "Ta3mya, or falafel, is a popular Egyptian dish made from ground fava beans mixed with herbs and spices, then deep-fried until crispy. It's often served in flatbread with tahini and pickles, perfect for a tasty, light meal.",
    isBookMark = false,
    recipeImage = R.drawable.food_ta3mya
)

val food2 = FoodModel(
    recipeName = "Koshari",
    description = "Koshari is a classic Egyptian dish made of rice, pasta, and lentils, topped with a spicy tomato sauce, crispy fried onions, and chickpeas. It's a hearty, flavorful meal and a favorite street food in Egypt!",
    isBookMark = false,
    recipeImage = R.drawable.food_koshari
)

val food3 = FoodModel(
    recipeName = "fool",
    description = "Fool (Ful Medames) is a traditional Egyptian dish made from slow-cooked fava beans, often seasoned with olive oil, lemon juice, garlic, and spices. It's a staple breakfast food, usually served with bread and various toppings like eggs or tahini. Simple, filling, and delicious!",
    isBookMark = false,
    recipeImage = R.drawable.food_fool
)


val foods = listOf(
    food1,
    food2,
    food3,
    food1,
    food2,
    food3,
    food1,
    food2,
    food3,
)

