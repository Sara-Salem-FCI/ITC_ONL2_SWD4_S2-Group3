package com.example.fitnessapp.presentation.screens.muscle_screen

import com.example.fitnessapp.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExercisesScreen() {
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
            "chest", "shoulders", "biceps",
            "triceps", "upper back", "lats",
            "calves", "hamstrings", "quads"
        )

        val imageIds = listOf(
            R.drawable.chest, R.drawable.shoulders, R.drawable.biceps,
            R.drawable.triceps, R.drawable.upper_back, R.drawable.lats,
            R.drawable.calves, R.drawable.hamstrings, R.drawable.abdominal
        )

        GridItems(exercises, imageIds)

        Spacer(modifier = Modifier.weight(1f)) // Adds spacing below content
    }
}

@Composable
fun GridItems(exercises: List<String>, imageIds: List<Int>) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        for (row in exercises.chunked(3).zip(imageIds.chunked(3))) { // 3 items per row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.first.zip(row.second).forEach { (exercise, imageId) ->
                    ExerciseItem(name = exercise, imageId = imageId)
                }
            }
        }
    }
}

@Composable
fun ExerciseItem(name: String, imageId: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { /* Handle click */ }
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

