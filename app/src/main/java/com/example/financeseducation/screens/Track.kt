package com.example.financeseducation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeseducation.R
import com.example.financeseducation.components.NavBar
import com.example.financeseducation.models.Lesson
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome

@Composable
fun TrackScreen(navController: NavController, lessons: List<Lesson>) {
    // Keep track of which lessons are completed
    val completedStates = remember { mutableStateListOf<Boolean>().apply {
        addAll(List(lessons.size) { false })
    }}

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(
                            bottomStart = 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
            ) {
                Image(
                    FontAwesome.Icon.faw_piggy_bank,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(115.dp, 100.dp)
                        .padding(top = 50.dp)
                )

                Text(
                    text = "Aprender",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            // Lista de lições
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 100.dp)
            ) {
                itemsIndexed(lessons) { index, lesson ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .padding(top = if (index == 0) 16.dp else 0.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        border = BorderStroke(width = 1.dp, Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = lesson.name,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.Black,
                                        textDecoration = if (completedStates[index]) TextDecoration.LineThrough else TextDecoration.None
                                    )
                                    Text(
                                        text = lesson.description,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Black,
                                        textDecoration = if (completedStates[index]) TextDecoration.LineThrough else TextDecoration.None
                                    )
                                }

                                // Checklist
                                Checkbox(
                                    checked = completedStates[index],
                                    onCheckedChange = { checked ->
                                        completedStates[index] = checked
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.teal_700),
                                        uncheckedColor = Color.Gray
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(
                                    onClick = { navController.navigate("lesson/${lesson.id}") },
                                    colors = ButtonDefaults.buttonColors(
                                        MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Text("Go", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            NavBar(navController)
        }

    }
}
