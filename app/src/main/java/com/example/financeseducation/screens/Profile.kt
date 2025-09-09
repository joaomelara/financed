package com.example.financeseducation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.financeseducation.components.NavBar
import com.example.financeseducation.database.repository.UsersRepository
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.platform.LocalContext
import com.example.financeseducation.models.Users

@Composable
fun Profile(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 70.dp, 16.dp, 115.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            val usersRepository = UsersRepository(context)
            val name = usersRepository.showName().nome

            Image(
                FontAwesome.Icon.faw_user_circle1,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Olá, ${name}",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(50.dp))

            EarningsExpensesInputs(usersRepository)

        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            NavBar(navController)
        }
    }
}

@Composable
fun EarningsExpensesInputs(
    usersRepository: UsersRepository
) {
    val context = LocalContext.current

    var incomeText by remember { mutableStateOf(usersRepository.showName().income.toString()) }
    var expensesText by remember { mutableStateOf(usersRepository.showName().expenses.toString()) }
    var income by remember { mutableStateOf(usersRepository.showName().income) }
    var expenses by remember { mutableStateOf(usersRepository.showName().expenses) }
    var error by remember { mutableStateOf<String?>(null) }
    var modified by remember { mutableStateOf(false) }

    fun parseCurrencyInput(s: String): Double {
        val normalized = s.replace("[^\\d.,]".toRegex(), "")
            .replace(",", ".")
        return normalized.toDoubleOrNull() ?: 0.0
    }

    fun computeSavingsRate(income: Double, expenses: Double): Int {
        if (income <= 0.0) return 0
        val rate = ((income - expenses) / income) * 100.0
        return rate.coerceIn(-999.0, 999.0).toInt()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = incomeText,
            onValueChange = { new ->
                // allow only digits, dots and commas
                val filtered = new.replace("[^\\d.,]".toRegex(), "")
                incomeText = filtered
                income = parseCurrencyInput(filtered)
                modified = true
                // reset error when corrected
                if (expenses <= income) error = null
            },
            label = { Text("Ganhos mensais (R$)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = expensesText,
            onValueChange = { new ->
                val filtered = new.replace("[^\\d.,]".toRegex(), "")
                expensesText = filtered
                expenses = parseCurrencyInput(filtered)
                error = if (expenses > income) "Expenses exceed earnings" else null
                modified = true
            },
            label = { Text("Gastos mensais (R$)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = error != null,
            modifier = Modifier.fillMaxWidth()
        )

        if (error != null) {
            Text(text = error!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 6.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        val savingsRate = computeSavingsRate(income, expenses)
        Text(
            text = "Porcentagem de economia: $savingsRate%",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (savingsRate >= 20) MaterialTheme.colorScheme.primary else Color.Unspecified
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            val id = usersRepository.showName().id
            val createdAt = usersRepository.showName().createdAt
            val income = incomeText.toDouble()
            val expenses = expensesText.toDouble()
            val name = usersRepository.showName().nome

            val users = Users(
                id = id,
                nome = name,
                income = income,
                expenses = expenses,
                createdAt = createdAt
            )
            usersRepository.saveIncomeAndExpenses(users)
                modified = false
        }, enabled = modified) {
            Text("Salvar")
        }
    }
}