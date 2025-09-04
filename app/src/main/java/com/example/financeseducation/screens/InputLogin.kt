package com.example.financeseducation.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome

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
            var email = remember {
                mutableStateOf("")
            }
            // Title
            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle
            TextField(
                value = "${email.value}",
                onValueChange = { novoValor ->
                    email.value = novoValor
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = {
                    Text(text = "Seu email")
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Green,
                    unfocusedPlaceholderColor = Color.Magenta
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Subtitle
            TextField(
                value = "${email.value}",
                onValueChange = { novoValor ->
                    email.value = novoValor
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = {
                    Text(text = "Seu email")
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Green,
                    unfocusedPlaceholderColor = Color.Magenta
                )
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Login Button
            Button(
                onClick = { /* Handle login */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Entrar", color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(8.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Registration Button
            Button(
                onClick = { /* Handle registration */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            ) {
                Text(text = "Voltar", color = MaterialTheme.colorScheme.onSecondary, modifier = Modifier.padding(8.dp))
            }
        }
    }
}