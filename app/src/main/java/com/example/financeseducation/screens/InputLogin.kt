package com.example.financeseducation.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeseducation.database.repository.UsersRepository
import com.example.financeseducation.models.Users

@Composable
fun InputLogin(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current
            val usersRepository = UsersRepository(context)
            var errorMessage = remember { mutableStateOf("") }

            var nome = remember {
                mutableStateOf("")
            }

            // Title
            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Subtitle
            TextField(
                value = nome.value,
                onValueChange = { novoValor ->
                    nome.value = novoValor
                    errorMessage.value = "" // Clear error message on input change
                },
                placeholder = {
                    Text(text = "Seu nome")
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                )
            )

            if (errorMessage.value.isNotEmpty()) {
                Text(
                    text = errorMessage.value,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            // Login Button
            Button(
                onClick = {
                    if (nome.value.isBlank()) {
                        errorMessage.value = "Por favor, insira seu nome." // Set error message
                    } else {
                        val users = Users(nome = nome.value)
                        usersRepository.save(users)
                        navController.navigate("Perfil") // Navigate to profile screen
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Entrar", color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(8.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Registration Button
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            ) {
                Text(text = "Voltar", color = MaterialTheme.colorScheme.onSecondary, modifier = Modifier.padding(8.dp))
            }
        }
    }
}