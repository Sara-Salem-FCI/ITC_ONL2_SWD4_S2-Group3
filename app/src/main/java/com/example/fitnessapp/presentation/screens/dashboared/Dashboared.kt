package com.example.fitnessapp.presentation.screens.dashboared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.screens.dashboared.components.CircularProgressIndicator
import com.example.fitnessapp.presentation.screens.dashboared.components.DiscoverSection


@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color.White

            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 5.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "Edit",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(21.dp))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Calories",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Remaining = Goal - Food + Exercise",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
            )

            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                CircularProgressIndicator(
                    progress = 2000f / 3000f,
                    //dynamic
                    remainingText = "300",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))

                Column {

                    BaseFoodExercise(R.drawable.baseline_flag_24, "Header Image", "Base Goal")

                    BaseFoodExercise(
                        R.drawable.dinner_icon,
                        "Header Image",
                        "Food (377)",
                        ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )

                    BaseFoodExercise(R.drawable.hot_icon, "Header Image", "Exercise (20)")
                }
            }
        }

        // three arrows
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 130.dp)
        ) {
            Arrow()
            Arrow()
            Arrow()
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(all = 15.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(21.dp))
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_add_circle_24),
                contentDescription = "Header Image",
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)

            )
            Text(
                text = "Add Hapit",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,

                )

        }

        DiscoverSection(navController)
    }
}
@Composable
fun BaseFoodExercise(
    painter: Int,
    contentDescription: String,
    text: String,
    colorFilter: ColorFilter? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = painter),
            contentDescription = contentDescription,
            colorFilter = colorFilter,
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
fun Arrow() {
    Image(
        painter = painterResource(id = R.drawable.baseline_call_received_24),
        contentDescription = "Header Image",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .height(40.dp)
            .width(40.dp)
    )
}

@Composable
@Preview
fun PreviewAll() {
    ProfileScreen(navController = rememberNavController())
}
