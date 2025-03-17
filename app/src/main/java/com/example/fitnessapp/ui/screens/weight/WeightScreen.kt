package com.example.fitnessapp.ui.screens.weight


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.components.DefaultButton
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun WeightScreen(
    onWeight: () -> Unit = {}
) {
    var weight by remember { mutableStateOf(56f) } // Initial weight
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {

        Text(
            "What is your weight?",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(vertical = 10.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${weight.toInt()}", textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = "Kg",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 34.dp)
                .weight(1f)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircularDial()
                drawPointer(weight)
            }

        }
        Slider(
            value = weight,
            onValueChange = { weight = it },
            valueRange = 55f..58f,
            steps = 2,
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Green
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        DefaultButton(
            text = "Let's go",
            onClick = {
                onWeight()
            }
        )
    }
}

fun DrawScope.drawCircularDial() {
    drawCircle(
        color = Color.Green,
        style = Stroke(width = 8f)
    )
    val radius = size.minDimension / 2
    val steps = listOf(55, 56, 57, 58)
    steps.forEachIndexed { index, step ->
        val angle = (index - 1) * 90f // 90-degree intervals
        val x = (radius * cos(Math.toRadians(angle.toDouble()))).toFloat() + center.x
        val y = (radius * sin(Math.toRadians(angle.toDouble()))).toFloat() + center.y
        drawContext.canvas.nativeCanvas.drawText(
            step.toString(),
            x,
            y,
            android.graphics.Paint().apply {

                textSize = 40f
                textAlign = android.graphics.Paint.Align.CENTER
            }
        )
    }
}

fun DrawScope.drawPointer(weight: Float) {
    val angle = (weight - 55) * 90f - 90f // Calculate angle based on weight
    val radius = size.minDimension / 2
    val pointerLength = radius * 0.6f
    val x = (pointerLength * cos(Math.toRadians(angle.toDouble()))).toFloat() + center.x
    val y = (pointerLength * sin(Math.toRadians(angle.toDouble()))).toFloat() + center.y
    drawLine(
        color = Color.Green,
        start = center,
        end = Offset(x, y),
        strokeWidth = 8f
    )
}

@Preview
@Composable
private fun Preview() {
    FitnessAppTheme {
        WeightScreen()
    }
}