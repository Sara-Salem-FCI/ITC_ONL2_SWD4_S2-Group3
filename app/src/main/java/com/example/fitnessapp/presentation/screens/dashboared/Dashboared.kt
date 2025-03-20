package com.example.fitnessapp.presentation.screens.dashboared



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
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
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Header Image",
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape)
                    .background(Color(android.graphics.Color.parseColor("#29E33C")))
            )

            Text(
                text = "myfitnesspal",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,

                )

            Image(
                painter = painterResource(id = R.drawable.baseline_notifications_24),
                contentDescription = "Header Image",
                colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)

            )
        }
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
                    .border(BorderStroke(1.dp, Color.LightGray))
                    .padding(horizontal = 5.dp, vertical = 3.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = "Edit",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 20.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color.LightGray))
                .padding(16.dp)
        ) {
            Text(
                text = "Calories",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Remaining = Goal - Food + Exercise",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircularProgressIndicator(
                    //
                    progress = 2000f / 3000f,
                    //dynamic
                    remainingText = "300",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_flag_24),
                            contentDescription = "Header Image",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Base Goal",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.dinner_icon),
                            contentDescription = "Header Image",
                            colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)

                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Food (377)",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {

                        Image(
                            painter = painterResource(id = R.drawable.hot_icon),
                            contentDescription = "Header Image",
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                        )
                        Text(
                            text = "Exercise (20)",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center, // Space evenly between items
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_call_received_24),
                contentDescription = "Header Image",
                colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_call_received_24),
                contentDescription = "Header Image",
                colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_call_received_24),
                contentDescription = "Header Image",
                colorFilter = ColorFilter.tint(Color(android.graphics.Color.parseColor("#29E33C"))),
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier

                .padding(all = 15.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Color.White)
                .border(BorderStroke(2.dp, Color.LightGray))
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
                color = Color.Black,
                fontWeight = FontWeight.Bold,

                )

        }

        DiscoverSection(navController)
    }
}

@Composable
@Preview
fun PreviewAll() {
    ProfileScreen(navController = rememberNavController())
}
