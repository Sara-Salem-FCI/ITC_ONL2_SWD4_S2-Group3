package com.example.fitnessapp.presentation.screens.userdata.level_screen.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.components.DefaultButton
import com.example.fitnessapp.presentation.screens.userdata.level_screen.models.LevelList
import com.example.fitnessapp.ui.theme.FitnessAppTheme


@Composable
fun LevelContent(
    onPersonLevel: (String) -> Unit,
    levelList: MutableList<LevelList>,
) {

    var personLevel = ""
    val isLevelSelected = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.physical_activity_level),
            style = MaterialTheme.typography.headlineMedium,
            color = colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 10.dp)
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var oldSelected: MutableState<Boolean>? = null

            items(levelList.size) { i ->
                val isSelected = remember { mutableStateOf(levelList[i].isSelected) }

                // changing the border color if it's false be
                val border = if (isSelected.value) {
                    BorderStroke(3.dp, colorScheme.primary)
                } else BorderStroke(3.dp, colorScheme.onBackground)

                RowElements(
                    levelList[i],
                    modifier = Modifier.clickable {
                        if (oldSelected?.value != null)
                            oldSelected?.value = false
                        oldSelected = isSelected
                        isSelected.value = true

                        personLevel = levelList[i].levelName
                    },
                    border = border
                )
            }
        }

        Column {
            DefaultButton(
                onClick = {
                    if (personLevel.isEmpty()) {
                        isLevelSelected.value = "Please select your level"
                    } else {
                        onPersonLevel(personLevel)
                    }
                },
                message = isLevelSelected.value
            )
        }
    }
}

@Composable
fun RowElements(
    levelList: LevelList,
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(3.dp, colorScheme.onBackground)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Image(
            modifier = Modifier
                .width(73.dp)
                .height(73.dp)
                .padding(end = 8.dp),
            painter = painterResource(
                id = levelList.levelImage
            ),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorScheme.primary),
        )

        CardElement(levelList.levelName, modifier, border)
    }
}

@Composable
fun CardElement(
    levelList: String,
    modifier: Modifier = Modifier,
    border: BorderStroke
) {
    Card(
        colors = CardDefaults.cardColors(colorScheme.surface),
        modifier = Modifier
            .width(227.dp)
            .height(61.dp),
        border = border,
        shape = CircleShape,

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = levelList,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        LevelContent(
            onPersonLevel = { _ -> },
            levelList = mutableListOf(
                LevelList(
                    levelName = "Beginner",
                    levelImage = R.drawable.ex_stretching,
                ),
                LevelList(
                    levelName = "Intermediate",
                    levelImage = R.drawable.ex_running,
                ),
                LevelList(
                    levelName = "Advanced",
                    levelImage = R.drawable.ex_exercise,
                )
            )
        )
    }
}