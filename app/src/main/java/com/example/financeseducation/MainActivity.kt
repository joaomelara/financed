package com.example.financeseducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financeseducation.ui.theme.FinancesEducationTheme

import com.example.financeseducation.screens.LoginScreen
import com.example.financeseducation.screens.InputLogin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancesEducationTheme {
                Surface {
                val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable(route = "login") { LoginScreen(navController) }
                        composable(route = "inputLogin") { InputLogin(navController) }
                    }
                }
            }
        }
    }
}
