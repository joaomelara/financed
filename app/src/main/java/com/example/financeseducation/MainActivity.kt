package com.example.financeseducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeseducation.database.repository.UsersRepository
import com.example.financeseducation.ui.theme.FinancesEducationTheme

import com.example.financeseducation.screens.LoginScreen
import com.example.financeseducation.screens.InputLogin
import com.example.financeseducation.screens.LessonDetailScreen
import com.example.financeseducation.screens.TrackScreen
import com.example.financeseducation.utils.loadLessons
import com.example.financeseducation.screens.Map
import com.example.financeseducation.screens.Profile
import com.example.financeseducation.screens.Converter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Carregar JSON aqui
        val lessons = loadLessons(this)

        setContent {
            FinancesEducationTheme {
          
                val context = LocalContext.current
                val usersRepository = UsersRepository(context)
                var destination = ""

                try {
                    destination = if(usersRepository.showName().toString().isNotEmpty()) {
                        "Perfil"
                    } else {
                        "login"
                    }
                } catch (t: Throwable) {
                    println(t)
                    destination = "login"
                }

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = destination
                ) {
                    composable(route = "login") { LoginScreen(navController) }
                    composable(route = "inputLogin") { InputLogin(navController) }
                    composable(route = "Perfil") { Profile(navController) }
                    composable(route = "Conversor") { Converter(navController) }
                    composable(route = "Aprender") { Map(navController) }
                    
                    // TrackScreen showing the list
                        composable(route = "trackScreen") {
                            TrackScreen(navController, lessons)
                        }

                        composable(route = "lesson/{lessonId}") { backStackEntry ->
                            val lessonId = backStackEntry.arguments?.getString("lessonId")?.toIntOrNull()
                            val lesson = lessons.find { it.id == lessonId }
                            if (lesson != null) {
                                LessonDetailScreen(lesson = lesson, navController = navController)
                            } else {
                                // Optional: handle lesson not found
                                Text("Lesson not found")
                            }
                        }
         
                }
            }
        }
    }
}
