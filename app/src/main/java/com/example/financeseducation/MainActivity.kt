package com.example.financeseducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeseducation.ui.theme.FinancesEducationTheme

import com.example.financeseducation.screens.LoginScreen
import com.example.financeseducation.screens.InputLogin
import com.example.financeseducation.screens.Map
import com.example.financeseducation.screens.Profile
import com.example.financeseducation.screens.Converter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancesEducationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable(route = "login") { LoginScreen(navController) }
                    composable(route = "inputLogin") { InputLogin(navController) }
                    composable(route = "Perfil") { Profile(navController) }
                    composable(route = "Conversor") { Converter(navController) }
                    composable(route = "Aprender") { Map(navController) }
                }
            }
        }
    }
}
