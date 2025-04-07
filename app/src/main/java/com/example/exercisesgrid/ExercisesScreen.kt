package com.example.exercisesgrid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ExercisesScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0C0C0C))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Exercises",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Spacer(modifier = Modifier.weight(1f)) // Pushes content down

        val exercises = listOf(
            "Chest", "Shoulders", "Biceps",
            "Triceps", "Back",
            "Forearm","Legs","Abdominal"
        )

        val imageIds = listOf(
            R.drawable.chest, R.drawable.shoulders, R.drawable.biceps,
            R.drawable.triceps, R.drawable.upper_back, R.drawable.forearm,
            R.drawable.hamstrings, R.drawable.abdominal
        )

        GridItems(exercises, imageIds, navController)

        Spacer(modifier = Modifier.weight(1f)) // Adds spacing below content
    }
}

@Composable
fun GridItems(
    exercises: List<String>,
    imageIds: List<Int>,
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        // Display 3 items per row
        for (row in exercises.chunked(3).zip(imageIds.chunked(3))) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.first.zip(row.second).forEach { (exercise, imageId) ->
                    ExerciseItem(name = exercise, imageId = imageId, navController = navController)
                }
            }
        }
    }
}

@Composable
fun ExerciseItem(name: String, imageId: Int, navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                // Navigate to the detail screen, passing the muscle name as an argument
                navController.navigate("exerciseDetails/$name")
            }
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.DarkGray)
        )
        Text(
            text = name,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}