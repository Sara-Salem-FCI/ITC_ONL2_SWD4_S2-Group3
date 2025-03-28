package com.example.fitnessapp.presentation.screens.userdata.set_goals_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetGoalsScreen(
    onSetGoals: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val items = listOf(
            R.drawable.loseweight,
            R.drawable.weightscale,
            R.drawable.machine
        )
        val goals = listOf("Lose Weight", "Gain Weight", "Maintain Weight")
        val selectedIndex = remember { mutableStateOf(-1) }


        Text(
            text = "Set Your Goals",
            style = MaterialTheme.typography.headlineMedium,
            color = colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Spacer(Modifier.height(10.dp))

        Column(
            Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            items.forEachIndexed { index, image ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                width = 2.dp,
                                color = if (selectedIndex.value == index) colorScheme.primary else Color.Gray,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                selectedIndex.value = index
                            }

                            .background(color = colorScheme.surface)
                    ) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "Goal Image",
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = goals[index],
                        modifier = Modifier.padding(top = 5.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorScheme.onBackground
                    )
                }
            }
        }
        
        DefaultButton(onClick = onSetGoals)
    }

}

@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        SetGoalsScreen()
    }
}