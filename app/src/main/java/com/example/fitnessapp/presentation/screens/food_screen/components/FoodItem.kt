package com.example.fitnessapp.presentation.screens.food_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.screens.food_screen.models.FoodModel
import com.example.fitnessapp.presentation.screens.food_screen.preview.food1
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun FoodItem(food: FoodModel,modifier: Modifier = Modifier) {

    var bookMark by remember {
        mutableIntStateOf(R.drawable.bookmark)
    }

    Box(
        modifier = modifier
            .padding(12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier
                .padding(start = 23.dp)
                .clickable {
                    println("Clicking Card...")
                }
                .border(2.dp, colorScheme.primary, shapes.large)
                .clip(shapes.large)
                .background(colorScheme.surface)
                .height(100.dp)
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .padding(start = 90.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = food.recipeName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorScheme.onSurface
                    )

                    Text(
                        text = food.description,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelSmall,
                        color = colorScheme.onSurface
                    )
                }

                Image(
                    modifier = Modifier
                        .clickable {
                            bookMark = if (!food.isBookMark) {
                                food.isBookMark = true
                                R.drawable.bookmark_fill
                            }
                            else {
                                food.isBookMark = false
                                R.drawable.bookmark
                            }
                            println("bookmark = ${food.isBookMark}")
                        }
                        .padding(end = 2.dp)
                        .size(30.dp),
                    painter = painterResource(id = bookMark),
                    colorFilter = ColorFilter.tint(colorScheme.primary),
                    contentDescription = "Food Image",
                )

            }
        }

        Image(
            modifier = Modifier
                .size(110.dp)
                .shadow(3.dp, shape = CircleShape, spotColor = colorScheme.onSurface)
                .clip(CircleShape),
            painter = painterResource(id = food.recipeImage),
            contentDescription = "Food Image",
        )
    }
}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        FoodItem(food1)
    }
}