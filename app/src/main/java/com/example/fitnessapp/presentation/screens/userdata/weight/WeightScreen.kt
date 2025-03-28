package com.example.fitnessapp.presentation.screens.userdata.weight

import android.graphics.Paint
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
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import java.lang.Math.toRadians
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WeightScreen(
    onWeight: () -> Unit = {}
) {
    var weight by remember { mutableFloatStateOf(70f) } // Default weight
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
                text = "${weight.toInt()}",
                textAlign = TextAlign.Center,
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

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 34.dp)
                .weight(1f)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircularDial(20, 180) // Before: drawCircularDial(30, 150)
                drawPointer(weight, 20, 180) // Before: drawPointer(weight, 30, 150)
            }
        }

        Slider(
            value = weight,
            onValueChange = { weight = it },
            valueRange = 20f..180f, // Before: 30f..150f
            steps = 160, // Before: steps = 120, adjusted for smoother control
            colors = SliderDefaults.colors(
                thumbColor = Color.Green,
                activeTrackColor = Color.Green
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        DefaultButton(
            onClick = {
                onWeight()
            }
        )
    }

}

fun DrawScope.drawCircularDial(minWeight: Int, maxWeight: Int) {
    drawCircle(
        color = Color.Green,
        style = Stroke(width = 8f)
    )

    val radius = size.minDimension / 2
    val totalSteps = maxWeight - minWeight
    val stepAngle = 360f / totalSteps // Before: 90f / totalSteps

    for (i in 0..totalSteps step 10) { // Before: step 20
        val weightValue = minWeight + i
        val angle = i * stepAngle - 90f
        val x = (radius * cos(toRadians(angle.toDouble()))).toFloat() + center.x
        val y = (radius * sin(toRadians(angle.toDouble()))).toFloat() + center.y

        drawContext.canvas.nativeCanvas.drawText(
            weightValue.toString(),
            x,
            y,
            Paint().apply {
                textSize = 40f
                textAlign = Paint.Align.CENTER
            }
        )
    }
}

fun DrawScope.drawPointer(weight: Float, minWeight: Int, maxWeight: Int) {
    val totalSteps = maxWeight - minWeight
    val stepAngle = 360f / totalSteps // Before: 90f / totalSteps
    val angle = (weight - minWeight) * stepAngle - 90f

    val radius = size.minDimension / 2
    val pointerLength = radius * 0.6f
    val x = (pointerLength * cos(toRadians(angle.toDouble()))).toFloat() + center.x
    val y = (pointerLength * sin(toRadians(angle.toDouble()))).toFloat() + center.y

    drawLine(
        color = Color.Green,
        start = center,
        end = Offset(x, y),
        strokeWidth = 8f
    )

}


@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        WeightScreen()
    }
}