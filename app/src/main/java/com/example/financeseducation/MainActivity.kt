package com.example.financeseducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeseducation.ui.theme.FinancesEducationTheme

import com.example.financeseducation.screens.LoginScreen
import com.example.financeseducation.screens.InputLogin
import com.example.financeseducation.screens.TrackScreen
import com.example.financeseducation.utils.loadLessons // importa a função que carrega JSON

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Carregar JSON aqui
        val lessons = loadLessons(this)

        setContent {
            FinancesEducationTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "trackScreen"
                    ) {
                        composable(route = "login") { LoginScreen(navController) }
                        composable(route = "inputLogin") { InputLogin(navController) }
                        // Passar a lista de lessons para a tela
                        composable(route = "trackScreen") {
                            TrackScreen(navController, lessons)
                        }
                    }
                }
            }
        }
    }
}
