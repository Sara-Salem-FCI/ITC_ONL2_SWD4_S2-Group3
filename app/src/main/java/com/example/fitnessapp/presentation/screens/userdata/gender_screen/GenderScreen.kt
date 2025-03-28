import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.fitnessapp.R
import com.example.fitnessapp.presentation.components.DefaultButton


@Composable
fun GenderScreen(onGender: (String) -> Unit) {

    val isGenderSelected = remember { mutableStateOf("") }


    val items = listOf(R.drawable.male, R.drawable.female)
    val gender = listOf("Male", "Female")
    val selectedIndex = remember { mutableStateOf(-1) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            text = "What's Your Gender ?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Column(
            Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {


            items.forEachIndexed { index, image ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                width = 2.dp,
                                color = if (selectedIndex.value == index) MaterialTheme.colorScheme.primary else Color.Gray,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                selectedIndex.value = index
                                showError = false
                            }
                            .background(color = MaterialTheme.colorScheme.surface)
                    ) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "Gender Image",
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = gender[index],
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            if (showError) {
                isGenderSelected.value = "Please select a gender"
            }
        }

        DefaultButton(
            onClick = {
                if (selectedIndex.value == -1) {
                    showError = true
                } else {
                    onGender(gender[selectedIndex.value])
                }
            }, message = isGenderSelected.value
        )
    }
}