package com.example.exercisesgrid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.size
import androidx.compose.ui.text.font.FontWeight

// Exercise data class with images instead of video
data class Exercise(
    val name: String,
    val description: String,
    val sets: String,
    val reps: String,
    val image1: Int, // First image
    val image2: Int  // Second image
)

fun getExercisesForMuscle(muscle: String): List<Exercise> {
    return when (muscle) {
        "Chest" -> listOf(
            Exercise("Dumbbell Press Flat", "Builds mid chest", "4 Sets", "10 Reps", R.drawable.chest_dumbel_1, R.drawable.chest_dumbel_2),
            Exercise("Machine Chest Press Incline", "Targets upper chest", "4 Sets", "12 Reps", R.drawable.machine_chest_incline_1, R.drawable.machine_chest_incline_2),
            Exercise("Cable High to Low", "Isolates lower chest", "3 Sets", "15 Reps", R.drawable.cable_machine_high_to_low_1, R.drawable.cable_machine_high_to_low_2),
            Exercise("Machine Fly Press", "Chest isolation movement", "3 Sets", "12 Reps", R.drawable.machine_fly_fitness_1, R.drawable.machine_fly_fitness_2)
        )
        "Shoulders" -> listOf(
            Exercise("Dumbbell Shoulders Press", "Builds shoulder size", "4 Sets", "10 Reps", R.drawable.dumbell_shoulders_press_1, R.drawable.dumbell_shoulders_press_2),
            Exercise("Cable Lateral Raises", "Targets side delts", "3 Sets", "12 Reps", R.drawable.cable_lateral_raises_1, R.drawable.cable_lateral_raises_2),
            Exercise("Dumbbell Lateral Raises", "Defines side delts", "3 Sets", "12 Reps", R.drawable.dumbell_lateral_raises_1, R.drawable.dumbell_lateral_raises_2),
            Exercise("Dumbbell Shrugs", "Traps isolation movement", "4 Sets", "15 Reps", R.drawable.dumbbell_shrugs_1, R.drawable.dumbbell_shrugs_2)
        )
        "Back" -> listOf(
            Exercise("Single Arm Row", "Builds lats and rhomboids", "4 Sets", "10 Reps", R.drawable.single_arm_row_1, R.drawable.single_arm_row_2),
            Exercise("Barbell Reverse Over Row", "Strengthens upper back", "4 Sets", "8 Reps", R.drawable.barbell_reverse_1, R.drawable.barbell_reverse_2),
            Exercise("Seated Cable Row V", "Targets mid back", "4 Sets", "12 Reps", R.drawable.seated_cable_row_v_1, R.drawable.seated_cable_row_v_2)
        )
        "Legs" -> listOf(
            Exercise("Barbell Squat", "Full leg compound", "4 Sets", "10 Reps", R.drawable.barbell_squat_1, R.drawable.barbell_squat_2),
            Exercise("Lying Hamstring Curls", "Targets hamstring muscles", "4 Sets", "12 Reps", R.drawable.lying_hamstring_curls_1, R.drawable.lying_hamstring_curls_2),
            Exercise("Leg Press", "Strengthens entire legs", "4 Sets", "12 Reps", R.drawable.leg_press_1, R.drawable.leg_press_2),
            Exercise("Leg Calf Raises", "Builds calf muscles", "4 Sets", "20 Reps", R.drawable.leg_calf_raises_1, R.drawable.leg_calf_raises_2),
            Exercise("Front Squats Barbell", "Quad focused squat", "4 Sets", "10 Reps", R.drawable.front_squats_barbell_1, R.drawable.front_squats_barbell_2),
            Exercise("Seated Leg Curl", "Isolates hamstrings", "4 Sets", "12 Reps", R.drawable.seated_leg_curl_1, R.drawable.seated_leg_curl_2),
            Exercise("Calf Raise Leg Press", "Calves under pressure", "4 Sets", "20 Reps", R.drawable.calf_raise_leg_press_1, R.drawable.calf_raise_leg_press_2),
            Exercise("Elevated Glute Bridge", "Builds strong glutes", "4 Sets", "15 Reps", R.drawable.glutes_1, R.drawable.glutes_2),
        )
        "Triceps" -> listOf(
            Exercise("Cable Push-down Bar", "Triceps definition move", "3 Sets", "12 Reps", R.drawable.cable_pushdown_bar_1, R.drawable.cable_pushdown_bar_2),
            Exercise("Cable Extensions", "Isolates tricep heads", "4 Sets", "10 Reps", R.drawable.cable_tricep_extensions_1, R.drawable.cable_tricep_extensions_2),
            Exercise("Dips", "Targets triceps bodyweight", "3 Sets", "10 Reps", R.drawable.dips_1, R.drawable.dips_2),
        )
        "Biceps" -> listOf(
            Exercise("Alternate Incline Dumbbell Curl", "Isolates long head", "3 Sets", "12 Reps", R.drawable.alternate_incline_dumbbell_curl_1, R.drawable.alternate_incline_dumbbell_curl_2),
            Exercise("Biceps Cable Curls", "Maintains constant tension", "4 Sets", "12 Reps", R.drawable.biceps_cable_curls_1, R.drawable.biceps_cable_curls_2),
            Exercise("Dumbbell Preacher Curl", "Strict curl movement", "3 Sets", "10 Reps", R.drawable.dumbbell_preacher_curl_1, R.drawable.dumbbell_preacher_curl_2),
            Exercise("Ez Bar Biceps Curl Wide Grip", "Outer bicep focus", "3 Sets", "12 Reps", R.drawable.ez_bar_biceps_curl_wide_grip_1, R.drawable.ez_bar_biceps_curl_wide_grip_2)
        )
        "Forearm" -> listOf(
            Exercise("Barbell Standing Back Wrist Curl", "Forearm mass builder", "3 Sets", "15 Reps", R.drawable.barbell_standing_back_wrist_curl_1, R.drawable.barbell_standing_back_wrist_curl_2),
            Exercise("Dumbbell One Arm Reverse Wrist Curl", "Targets wrist extensors", "3 Sets", "12 Reps", R.drawable.dumbbell_one_arm_revers_wrist_curl_1, R.drawable.dumbbell_one_arm_revers_wrist_curl_2),
            Exercise("Dumbbell One Arm Seated Neutral Wrist Curl", "Forearm isolation curl", "3 Sets", "12 Reps", R.drawable.dumbbell_one_arm_seated_neutral_wrist_curl_1, R.drawable.dumbbell_one_arm_seated_neutral_wrist_curl_2),
        )
        "Abdominal" -> listOf(
            Exercise("Crunch Floor", "Targets upper abs", "3 Sets", "20 Reps", R.drawable.crunch_floor_1, R.drawable.crunch_floor_2),
            Exercise("Russian Twist Weighted Ball", "Oblique twist core", "3 Sets", "16 Reps", R.drawable.russian_twist_weighted_ball_1, R.drawable.russian_twist_weighted_ball_2),
            Exercise("Stomach Muscles Isolated", "Lower abs activation", "3 Sets", "15 Reps", R.drawable.stomach_muscles_isolated_1, R.drawable.stomach_muscles_isolated_2),
        )
        else -> listOf(
            Exercise("Default Exercise", "Example fallback move", "3 Sets", "15 Reps", R.drawable.barbell_standing_back_wrist_curl_1, R.drawable.barbell_standing_back_wrist_curl_2)
        )
    }
}


@Composable
fun ExerciseDetailScreen(muscle: String) {
    val exercises = getExercisesForMuscle(muscle)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0C0C0C))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${muscle.replaceFirstChar { it.uppercase() }} Exercises",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(exercises) { index, exercise ->
                ExerciseDetailItem(
                    name = exercise.name,
                    description = exercise.description,
                    sets = exercise.sets,
                    reps = exercise.reps,
                    image1 = exercise.image1,
                    image2 = exercise.image2,
                    isFirstItem = index == 0
                )
            }
        }
    }
}

@Composable
fun ExerciseDetailItem(
    name: String,
    description: String,
    sets: String,
    reps: String,
    image1: Int,
    image2: Int,
    isFirstItem: Boolean
) {
    if (!isFirstItem) {
        HorizontalDivider(thickness = 1.dp, color = Color(0x2DFFFFFF))
    }

    Row(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 7.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(text = name, color = Color.White, fontSize = 19.sp, fontWeight=FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 7.dp)
            )
            Text(text = description, color = Color.LightGray, fontSize = 14.sp,
                modifier = Modifier
                    .padding(vertical = 1.dp))

            Text(text = sets, color = Color.LightGray, fontSize = 17.sp,
                modifier = Modifier
                    .padding(vertical = 5.dp))

            Text(text = reps, color = Color.LightGray, fontSize = 17.sp,
                modifier = Modifier
                    .padding(vertical = 5.dp))

        }
        Box(
            modifier = Modifier.size(160.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedImage(image1, image2, imageSize = 160.dp)
        }
    }
}

@Composable
fun AnimatedImage(image1: Int, image2: Int, imageSize: Dp) {
    var isPlaying by remember { mutableStateOf(false) }
    var currentImage by remember { mutableIntStateOf(image1) }

    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            delay(700)
            currentImage = if (currentImage == image1) image2 else image1
        }
    }

    Box(
        modifier = Modifier
            .size(imageSize)
            .clickable { isPlaying = !isPlaying },
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = currentImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { isPlaying = !isPlaying }
        )

        Icon(
            painter = painterResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
            contentDescription = "Play/Pause",
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.BottomStart)
                .padding(5.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}